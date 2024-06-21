package kr.co.ktds.orchestra.service;

import kr.co.ktds.orchestra.mapper.WBSMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WBSService {
    @Autowired
    WBSMapper wbsMapper;

    static List<Date> holiDateList;
    static int MAX_LEVEL = 0;

    public List<Map<String, Object>> getWBSList(String date) {

        holiDateList = wbsMapper.getHolidate();
        List<Map<String, Object>> wbsData = wbsMapper.getWBSList();
        List<Integer> subTaskID = new ArrayList<Integer>();
        getMaxLevel(wbsData);

        // 조회날짜를 가져옴, Default는 현재 날짜
        Date dTodayDate = null;
        String sTodayDate = date;
        if( sTodayDate != null && !sTodayDate.isEmpty() ){
            try {
                dTodayDate = getDateFormat(sTodayDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            dTodayDate = new Date();
        }

        for(int i=0; i<wbsData.size(); i++) {
            try {
                Date planStartDate = getDateFormat(wbsData.get(i).get("plan_start_date").toString());           // 계획시작일
                Date planEndDate = getDateFormat(wbsData.get(i).get("plan_end_date").toString());               // 계획종료일
                float weight = Float.parseFloat(wbsData.get(i).get("weight").toString());                       // 가중치
                float resultProgress = Float.parseFloat(wbsData.get(i).get("result_progress").toString());      // 실제진척율

                // 산식 반영
                // 1. 계획소요기간(plan_day), 휴일을 뺀 Working day로 계산
                int planDay = getWorkingDays(planStartDate, planEndDate);
                wbsData.get(i).put("plan_day", planDay);

                // 2. 계획진척율(plan_progress)
                float planProgress = 0.0f;
                subTaskID = getSubTaskID(wbsData, (int)wbsData.get(i).get("id"));

                // 2.1 최하위 Task MAX_LEVEL 이면 최하위 Task
                if(subTaskID.isEmpty()) {
                    //   조회날짜보다 계획종료일이 크거나 같은 1
                    if( dTodayDate.after( planEndDate ) || dTodayDate.equals(planEndDate) ){
                        planProgress = 1.0f;
                    }
                    //   조회날짜보다 계획시작일이 작으면 0
                    else if( dTodayDate.before( planStartDate ) ){
                        planProgress = 0.0f;
                    }
                    //   계획시작일이 조회날짜이면 (계획시작일부터 조회날짜가의 일수) / 계획작업일수
                    else if( dTodayDate.equals( planStartDate ) ){
                        planProgress = (float)getWorkingDays(dTodayDate, planStartDate) / planDay;
                    }
                    //   위 조건이 다 아니면 (계획시작일부터 조회날짜까지의 일수) / 계획소요기간
                    else {
                        planProgress = (float)getWorkingDays(planStartDate, dTodayDate) / planDay;
                    }
                    planProgress*=100;
                    wbsData.get(i).put("plan_progress", planProgress);  // 계획진척율(100%)
                    // result_progress는 상단에 wbsData에서 불러왔으므로 정의안함.(최하위task는 입력된 실제진척율사용)
                } else {
                    resultProgress = 0.0f;  // 실제진척률(100%) 초기화
                    for( int j=0; j<subTaskID.size(); j++ ){
                         planProgress += (float)wbsData.get(subTaskID.get(j)).get("plan_weight_progress");      // 하위task의 계획진척율(가중치)의 합
                        resultProgress += (float)wbsData.get(subTaskID.get(j)).get("result_weight_progress");   // 하위task의 실제진척율(가중치)의 합
                    }
                    wbsData.get(i).put("plan_progress", planProgress);                                          // 계획진척율(100%) = 하위task의 계획진척율(가중치)의 합
                    wbsData.get(i).put("result_progress", resultProgress);                                      // 실제진척율(100%) = 하위task의 실제진척율(가중치)의 합
                }
                wbsData.get(i).put("plan_weight_progress", (float)planProgress * weight / 100);                 // 계획진척율(가중치)
                wbsData.get(i).put("result_weight_progress", (float)resultProgress * weight / 100);             // 실제진척률(가중치)

                // 3.달성율 계산
                float achievementRate = 0.0f;
                // 3.1 계획진척율(100%)가 0이고, 실제진척율(100%)가 0이면 빈칸
                if(planProgress == 0.0 && resultProgress == 0.0) {
                    achievementRate = 0.0f;

                // 3.2 계획진척율(100%)가 0이면, 실제진척율(100%)의 값
                } else if(planProgress == 0.0) {
                    achievementRate = resultProgress;

                // 3.3 계획진척율(100%)가 0이아니면 실제진척율(100%)/계획진척율(100%)
                } else {
                    achievementRate = (float)resultProgress / planProgress * 100;
                }

                wbsData.get(i).put("achievement_rate", achievementRate);    // 달성율
                
//                System.out.println(wbsData.get(i).entrySet());
            } catch(Exception e) {
                e.printStackTrace();
            }

        }


        return wbsData;
    }

    private int getWorkingDays(Date startDate, Date endDate ) throws Exception {
        Calendar start = Calendar.getInstance();
        start.setTime( startDate );

        Calendar end = Calendar.getInstance();
        end.setTime( endDate );

        int workingDays = 0;

        if( startDate.equals(endDate) ){
            workingDays = 1;
        }else{
            while (!start.after(end)) {
                int day = start.get(Calendar.DAY_OF_WEEK);
                if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY) && getHolidayCheck(start)) {
                    workingDays++;
                }
                start.add(Calendar.DATE, 1);
            }
        }
        return workingDays;
    }

    private boolean getHolidayCheck( Calendar checkDate ){

        if( holiDateList == null || holiDateList.isEmpty() ){
            return true;
        }else{
            for( int i=0; i<holiDateList.size(); i++ ){
                if( checkDate.getTime().equals( holiDateList.get(i) ) ){
                    return false;
                }
            }
        }
        return true;
    }

    private Date getDateFormat( String sDate ) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return sDate.isEmpty() ? null : format.parse( sDate );
    }

    private void getMaxLevel( List<Map<String, Object>> wbsData ){
        MAX_LEVEL = (int) wbsData.get(0).get("lv");
    }
    //    private void getIsLowLevel( List<Map<String, Object>> wbsData ){
//        MAX_LEVEL = (int) wbsData.get(0).get("lv");
//    }
    private List<Integer> getSubTaskID( List<Map<String, Object>> wbsData, int id ){

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<wbsData.size(); i++) {

            if( wbsData.get(i).get("parent_id") != null ){
                int nid = (int) wbsData.get(i).get("parent_id");
                if ( nid == id ){
                    list.add(i);
                }
            }
        }

        return list;
    }
}
