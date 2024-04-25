package hello.shoppingmallproject.controller;

import hello.shoppingmallproject.domian.Member;
import hello.shoppingmallproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMembersForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form, Model model){

        Member member = new Member();
        member.setId(form.getId());
        member.setPw(form.getPw());
        member.setName(form.getName());
        member.setNickname(form.getNickname());
        member.setEmail(form.getEmail());
        member.setPhone(form.getPhone());
        member.setAddr1(form.getAddr1());
        member.setAddr2(form.getAddr2());
        member.setPoint(0);

        try {
            memberService.join(member);
            return "redirect:/";
        }catch (Exception e){
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("message",e.getMessage());
            model.addAttribute("searchUrl","/members/new");
            return "members/message";
        }

    }


    @GetMapping("members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}