package kr.co.ktds.orchestra.service;

import kr.co.ktds.orchestra.mapper.SettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SettingService {
    @Autowired
    SettingMapper settingMapper;

    public List<Map<String, Object>> getProjects() {
        return settingMapper.getProjects();
    }

    public List<Map<String, Object>> getSettings(int projectId) {
        return settingMapper.getSettings(projectId);
    }

    @Transactional
    public Object setSettings(List<Map<String, Object>> body, int projectId) {
        for(int i=0; i<body.size(); i++) {
            settingMapper.setSettings(body.get(i), projectId);
        }
        return getSettings(projectId);
    }
}
