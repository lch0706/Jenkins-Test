package kr.co.ktds.orchestra.dto.dashboard;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WidgetInput {
  private Integer dashboardID;
  private Integer project_id;
  private boolean current;
  private String name;
  private Integer position;
  private List<Widget> grid; 
}
