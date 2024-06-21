package kr.co.ktds.orchestra.redmine;

public enum DefaultColumns {
    // fixed columns
    project("프로젝트"),
    id("ID"),
    tracker("유형"),
    status("상태"),
    priority("우선순위"),
    author("저자"),
    assigned_to("담당자"),
    category("업무구분"),
    fixed_version("단계"),
    parent("상위 일감"),
    subject("제목"),
    start_date("시작일"),
    due_date("종료일"),
    estimated_hours("추정 시간"),
    done_ratio("진척도"),
    description("설명");

    // Redmine Fixed Default Columns
    public String columns;

    DefaultColumns(String columns) {
        this.columns = columns;
    }

    public String getColumns() {
        return this.columns;
    }

}
