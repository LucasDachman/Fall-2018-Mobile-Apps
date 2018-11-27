package com.lucasdachman.coffee;

public class CoffeeShop {
    String coffeeShop;
    String coffeeShopURL;

    private void setCoffeeInfo(int crowd) {
        switch (crowd){
            case 0: //popular
                coffeeShop="Starbucks";
                coffeeShopURL="https://www.starbucks.com";
                break;
            case 1: //cycling
                coffeeShop="Amante";
                coffeeShopURL="http://www.amantecoffee.com/";
                break;
            case 2: //hipster
                coffeeShop="Ozo";
                coffeeShopURL="https://ozocoffee.com";
                break;
            case 3: //tea
                coffeeShop="Pekoe";
                coffeeShopURL="http://www.pekoesiphouse.com";
                break;
            case 4: //hippie
                coffeeShop="Trident";
                coffeeShopURL="http://www.tridentcafe.com";
                break;
            default:
                coffeeShop="none";
                coffeeShopURL="https://www.google.com/search?q=boulder+coffee+shops&ie=utf8&oe=utf-8";
        }
    }

    public void setCoffeeShop(int crowd) {
        setCoffeeInfo(crowd);
    }

    public String getCoffeeShop() {
        return coffeeShop;
    }

    public void setCoffeeShopURL(int crowd) {
        setCoffeeInfo(crowd);
    }

    public String getCoffeeShopURL() {
        return coffeeShopURL;
    }
}
