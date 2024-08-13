package com.coding404.myweb.controller;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.topic.TopicService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    @Qualifier("topicService") //이름으로연결
    private TopicService topicService;

    //목록
    @GetMapping("/topicList")
    public String topicList(Model model, Criteria cri) {

        //현재 로그인되어 있는 사람 아이디가 admin이라고 가정하고
        String userId = "admin";
        if (cri.getListType() == null) {
            cri.setListType("me");
        } else if (cri.getListType().equals("all")) {
            userId = "";
        }
        System.out.println("---------------------------------------");
        System.out.println(userId);
        ArrayList<TopicVO> list = topicService.getList(userId, cri);
        model.addAttribute("list", list);
        //페이지VO
        int total = topicService.getTotal(userId, cri); //전체게시글 수
        PageVO pageVO = new PageVO(cri, total); //페이지네이션
        System.out.println(pageVO.toString());
        model.addAttribute("pageVO", pageVO);
        return "topic/topicList";
    }

    //등록
    @GetMapping("/topicReg")
    public String topicReg() {
        return "topic/topicReg";
    }

    //수정
    @GetMapping("/topicModify")
    public String topicModify() {
        return "topic/topicModify";
    }

    //상세
    @GetMapping("/topicDetail")
    public String topicDetail(@RequestParam("topicId") int topicId,
                              Model model) {

        TopicVO vo = topicService.getDetail(topicId);
        model.addAttribute("vo", vo);

        return "topic/topicDetail";
    }

    //등록요청
    @PostMapping("/registForm")
    public String registForm(TopicVO vo,
                             RedirectAttributes ra) {

        //서버측에서 유효성 검사 진행가능

        int result = topicService.topicInsert(vo);

        if (result == 1) {
            ra.addFlashAttribute("msg", "정상 등록되었습니다");
        } else {
            ra.addFlashAttribute("msg", "등록에 실패했습니다. 1577-1577 문의해 주세요.");
        }

        return "redirect:/topic/topicList"; //다시 목록을 태워서 나감(데이터를 들고)
    }

    //상품 수정기능 - 반드시 필요한값은 PK
    @PostMapping("/topicUpdate")
    public String topicUpdate(TopicVO vo,
                              RedirectAttributes ra) {

        //업데이트
        int result = topicService.topicUpdate(vo);
        if (result == 1) {
            ra.addFlashAttribute("msg", "수정 되었습니다");
        } else {
            ra.addFlashAttribute("msg", "수정에 실패했습니다");
        }

        return "redirect:/topic/topicDetail?topicId=" + vo.getTopicId(); //상세화면은 id값을 필요로 함
    }

    //상품 삭제기능 -
    @PostMapping("/topicDelete")
    public String topicDelete(@RequestParam("topicId") int topicId) {

        topicService.topicDelete(topicId);

        return "redirect:/topic/topicList";
    }

}
