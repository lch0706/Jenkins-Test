package kr.co.ktds.orchestra.dto.dashboard;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Widget {
  private String categoryName;
  // private String chart;
  private String component_name;
  private Integer h;
  private Integer I;
  private Integer position;
  // private Integer id;
  private String project_id;
  private Integer query_id;
  private List<String> widget_size_value;
  private String widget_name;
  private String background_color_value;
  private String font_color_value;
  private String type;
  private boolean usedelete;
  private Integer w;
  private Integer widget_id;
  private Integer widget_type_id;
  private Integer x;
  private Integer y;
  
  @Data
  @ToString
  public class widgetSizeValue {
    private String val;
  }
}
