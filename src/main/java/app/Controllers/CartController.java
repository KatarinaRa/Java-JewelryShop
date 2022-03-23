package app.Controllers;

import app.Persistance.Entities.*;
import app.Services.*;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final UserService userService;
    private final CartService cartService;
    private final CartProductService cartProductService;
    private final CartPersService cartPersService;
    private final ProductService productService;
    private final CuponsService cuponsService;

    public CartController(UserService userService,
                          CartService cartService,
                          CartProductService cartProductService,
                          ProductService productService,
                          CuponsService cuponsService,
                          CartPersService cartPersService) {
        this.userService = userService;
        this.cartProductService = cartProductService;
        this.cartService = cartService;
        this.productService = productService;
        this.cuponsService = cuponsService;
        this.cartPersService = cartPersService;
    }

    @GetMapping("/getCart")
    public String cart(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Cart cart = user.getCart();
        List<CartProduct> cartProducts = cartProductService.findByCart(cart);
        List<CartPers> cartPersList = cartPersService.findByCart(cart);
        cartService.updateCart(cart);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("cartPers", cartPersList);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/addToCart")
    public String addProduct(@ModelAttribute("product") Product product,
                             Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        product = productService.getProductById(product.getId()).get();
        CartProduct cartProduct = cartProductService.addProduct(product, user);
        model.addAttribute("addProductSuccess", true);
        return "redirect:/shop/viewproduct/" + product.getId();
    }

    @PostMapping("/updateCartProduct")
    public String updateCart(
            @ModelAttribute("id") UUID id,
            @ModelAttribute("qty") int qty
    ) {
        CartProduct cartProduct = cartProductService.findById(id).get();
        cartProduct.setQty(qty);
        cartProductService.updateCartProduct(cartProduct);
        return "redirect:/cart/getCart";
    }

    @PostMapping("/addCode")
    public String addCode(
            @ModelAttribute("cart") Cart cart,
            @ModelAttribute("code") String code
    ) {
        Cupons cupons = new Cupons();
        cupons = cuponsService.findByCode(code);
        if(cupons != null && cupons.isActive()){
            double total = (cart.getTotal()*cupons.getPercent())/100;
            cart.setTotal(total);
            return "redirect:/cart/getCart";
        }
        else {
            return "redirect:/cart/getCart";
        }
    }

    @GetMapping("/removeProduct")
    public String removeProduct(@RequestParam("id") UUID id) {
        cartProductService.removeCartProduct(cartProductService.findById(id).get());
        return "redirect:/cart/getCart";
    }
}
