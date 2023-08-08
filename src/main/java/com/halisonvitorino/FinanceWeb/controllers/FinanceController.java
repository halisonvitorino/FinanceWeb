package com.halisonvitorino.FinanceWeb.controllers;

import com.halisonvitorino.FinanceWeb.models.Transacao;
import com.halisonvitorino.FinanceWeb.models.Usuario;
import com.halisonvitorino.FinanceWeb.repository.TransacaoRep;
import com.halisonvitorino.FinanceWeb.repository.UsuarioRep;
import com.halisonvitorino.FinanceWeb.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FinanceController {

    @Autowired
    private TransacaoRep transacaoRep;
    @Autowired
    private UsuarioRep usuarioRep;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    // Pagina Inicial
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Pagina login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Pagina Novo Usuario
    @GetMapping("/novoUsuario")
    public String novoUsuario() {
        return "novoUsuario";
    }

    // Pagina Lista Transaçoes -------------------------------------------------------------------------------------
    @RequestMapping("/listarTransacoes")
    public ModelAndView listaTransacoes() {
        ModelAndView modelAndView = new ModelAndView("/listarTransacoes");
        Iterable<Transacao> transacao = transacaoRep.findAll();
        modelAndView.addObject("transacao", transacao);
        return modelAndView;
    }

    // Nova transação
    @RequestMapping(value = "/cadastrarTransacoes", method = RequestMethod.GET)
    public String transacoes() {
        return "cadastrarTransacoes";
    }

    // Salvar transação
    @RequestMapping(value = "/cadastrarTransacoes", method = RequestMethod.POST)
    public String cadastroTransacao(Transacao transacao) {
        transacaoRep.save(transacao);
        return "redirect:/listarTransacoes";
    }

    @RequestMapping(value = "/alterarTransacoes/{id}", method = RequestMethod.GET)
    public ModelAndView alterarTransacoes(@PathVariable("id") long id) {
        Transacao transacao = transacaoRep.findById(id);
        ModelAndView modelAndView = new ModelAndView("/alterarTransacoes");
        modelAndView.addObject("transacao", transacao);
        return modelAndView;
    }

    @RequestMapping(value = "/alterarTransacoes/{id}", method = RequestMethod.POST)
    public String alteraTransacoes(@Validated Transacao transacao, BindingResult result,
            RedirectAttributes redirectAtributes) {
        transacaoRep.save(transacao);
        return "redirect:/listarTransacoes";
    }

    @RequestMapping("/excluiTransacoes")
    public String excluiTransacoes(long id) {
        Transacao transacao = transacaoRep.findById(id);
        transacaoRep.delete(transacao);
        return "redirect:/listarTransacoes";
    }

    //Telas de Graficos ---------------------------------------------------------------------------
    @RequestMapping("/graficoLinha")
    public String graficoLinha(Model model) {
        model.addAttribute("transacao", transacaoRep.somarValoresPorDescricao());
        return "graficoLinha";
    }

     @RequestMapping("/graficoPizza")
    public String graficoPizza(Model model) {
        model.addAttribute("transacao", transacaoRep.somarValoresPorDescricao());
        return "graficoPizza";
    }

    @RequestMapping("/graficoColunaDebitoCredito")
    public String graficoColunaDebitoCredito(Model model) {
        model.addAttribute("transacao", transacaoRep.somarValoresPorTipo());
        return "graficoColunaDebitoCredito";
    }

    // Pagina Lista Usuarios
    // --------------------------------------------------------------------------------------------
    @RequestMapping("/listaUsuario")
    public ModelAndView listaUsuario() {
        ModelAndView modelAndView = new ModelAndView("/listaUsuario");
        Iterable<Usuario> usuario = usuarioRep.findAll();
        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }

    @RequestMapping(value = "/alteraUsuario/{id}", method = RequestMethod.POST)
    public String alteraUsuario(@Validated Usuario usuario, BindingResult result,
            RedirectAttributes redirectAtributes) {
        usuarioRep.save(usuario);
        return "redirect:/listaUsuario";
    }

    @RequestMapping("/excluiUsuario")
    public String excluiUsuario(long id) {
        Usuario usuario = usuarioRep.findById(id);
        usuarioRep.delete(usuario);
        return "redirect:/listaUsuario";
    }

}
