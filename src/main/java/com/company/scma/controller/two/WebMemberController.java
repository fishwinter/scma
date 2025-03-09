package com.company.scma.controller.two;

import com.company.scma.common.dto.two.GetVideoListDTO;
import com.company.scma.common.dto.two.GetWebMemberListDto;
import com.company.scma.common.dto.two.OperateItemDto;
import com.company.scma.common.vo.Result;
import com.company.scma.common.vo.two.VideoDetailVo;
import com.company.scma.common.vo.two.VideoListItemVO;
import com.company.scma.common.vo.two.WebMemberListItemVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 网站会员接口
 */
@RestController
@RequestMapping(value = "/web/webMember", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebMemberController {

    /**
     * 获取会员列表接口
     * @param getWebMemberListDto
     * @return
     */
    @RequestMapping(value = "/getWebMemberList", method = RequestMethod.POST)
    public Result<List<WebMemberListItemVo>> getWebMemberList(@RequestBody GetWebMemberListDto getWebMemberListDto){
        return null;
    }

    /**
     * 重置会员密码接口
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/resetMemberPassword", method = RequestMethod.POST)
    public Result<String> resetMemberPassword(@RequestParam("memberId") String memberId){
        return null;
    }

    /**
     * 启用/禁用会员接口
     * @param operateItemDto
     * @return
     */
    @RequestMapping(value = "/enableMember", method = RequestMethod.POST)
    public Result<String> enableMember(@RequestBody OperateItemDto operateItemDto){
        return null;
    }
}
