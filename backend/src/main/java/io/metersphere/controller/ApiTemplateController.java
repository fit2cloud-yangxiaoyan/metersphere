package io.metersphere.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.metersphere.base.domain.ApiTemplate;
import io.metersphere.base.domain.TestCaseTemplate;
import io.metersphere.base.domain.TestCaseTemplateWithBLOBs;
import io.metersphere.commons.constants.OperLogConstants;
import io.metersphere.commons.constants.OperLogModule;
import io.metersphere.commons.utils.PageUtils;
import io.metersphere.commons.utils.Pager;
import io.metersphere.controller.request.BaseQueryRequest;
import io.metersphere.controller.request.UpdateCaseFieldTemplateRequest;
import io.metersphere.dto.ApiTemplateDTO;
import io.metersphere.dto.TestCaseTemplateDao;
import io.metersphere.log.annotation.MsAuditLog;
import io.metersphere.service.ApiTemplateService;
import io.metersphere.service.ApiTemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("field/template/api")
@RestController
public class ApiTemplateController {

    @Resource
    private ApiTemplateService apiTemplateService;

    @PostMapping("/add")
    @MsAuditLog(module = OperLogModule.WORKSPACE_TEMPLATE_SETTINGS_API, type = OperLogConstants.CREATE, content = "#msClass.getLogDetails(#request.id)", msClass =ApiTemplateService.class)
    public void add(@RequestBody UpdateApiFieldTemplateRequest request) {
        apiTemplateService.add(request);
    }

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<ApiTemplate>> list(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody BaseQueryRequest request) {
        Page<List<TestCaseTemplateWithBLOBs>> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, apiTemplateService.list(request));
    }

    @GetMapping("/delete/{id}")
    @MsAuditLog(module = OperLogModule.WORKSPACE_TEMPLATE_SETTINGS_API, type = OperLogConstants.DELETE, beforeEvent = "#msClass.getLogDetails(#id)", msClass = ApiTemplateService.class)
    public void delete(@PathVariable(value = "id") String id) {
        apiTemplateService.delete(id);
    }

    @PostMapping("/update")
    @MsAuditLog(module = OperLogModule.WORKSPACE_TEMPLATE_SETTINGS_API, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#request.id)", content = "#msClass.getLogDetails(#request.id)", msClass = ApiTemplateService.class)
    public void update(@RequestBody UpdateApiFieldTemplateRequest request) {
        apiTemplateService.update(request);
    }

    @GetMapping("/option/{workspaceId}")
    public List<ApiTemplate> list(@PathVariable String workspaceId) {
        return apiTemplateService.getOption(workspaceId);
    }

    @GetMapping("/get/relate/{projectId}")
    public ApiTemplateDTO getTemplate(@PathVariable String projectId) {
        return apiTemplateService.getTemplate(projectId);
    }
}
