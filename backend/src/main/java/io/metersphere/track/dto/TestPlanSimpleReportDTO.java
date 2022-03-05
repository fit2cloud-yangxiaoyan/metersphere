package io.metersphere.track.dto;

import io.metersphere.api.dto.automation.TestPlanFailureApiDTO;
import io.metersphere.api.dto.automation.TestPlanFailureScenarioDTO;
import io.metersphere.base.domain.IssuesDao;
import io.metersphere.base.domain.TestPlanReportContent;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class TestPlanSimpleReportDTO extends TestPlanReportContent {
    private String name;

    private int executeCount;
    private int passCount;
    private String summary;
    private String config;

    private TestPlanFunctionResultReportDTO functionResult;
    private TestPlanApiResultReportDTO apiResult;
    private TestPlanLoadResultReportDTO loadResult;

    List<TestPlanCaseDTO> functionFailureCases;
    List<TestPlanCaseDTO> functionAllCases;
    List<IssuesDao> issueList;
    List<TestPlanFailureApiDTO> apiFailureCases;
    List<TestPlanFailureApiDTO> apiAllCases;
    List<TestPlanFailureScenarioDTO> scenarioFailureCases;
    List<TestPlanFailureScenarioDTO> scenarioAllCases;
    List<TestPlanLoadCaseDTO> loadAllCases;
    List<TestPlanLoadCaseDTO> loadFailureCases;
    List<TestPlanFailureApiDTO> errorReportCases;
    List<TestPlanFailureScenarioDTO> errorReportScenarios;

    public int getPassCount() {
        if (passCount == 0 && getPassRate() != null && getCaseCount() != null){
            BigDecimal pr = new BigDecimal(getPassRate());
            BigDecimal cc = new BigDecimal(getCaseCount());
            passCount = pr.multiply(cc).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
        }
        return passCount;
    }

    public int getExecuteCount() {
        if (executeCount == 0 && getExecuteRate() != null && getCaseCount() != null){
            BigDecimal pr = new BigDecimal(getExecuteRate());
            BigDecimal cc = new BigDecimal(getCaseCount());
            executeCount = pr.multiply(cc).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
        }
        return executeCount;
    }
}
