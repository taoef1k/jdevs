package id.vimona.demo.jetdevs_test.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;


@Controller
public class HomeController {

  @GetMapping("/")
  @ResponseBody
  public void index(HttpServletResponse httpServletResponse) throws IOException {
    String homeURL = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
    httpServletResponse.sendRedirect(homeURL + "/swagger-ui/index.html");
  }

}
