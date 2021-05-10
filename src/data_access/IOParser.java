package data_access;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.domain.*;
import model.utilities.FoodCostPair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class IOParser {

    public static Node toRestaurantXMLNode(Restaurant restaurant, Document doc) {
        Element userElem = doc.createElement("User");
        //userElem.setAttribute("id", String.valueOf(user.getId()));
        userElem.setAttribute("id",String.valueOf(restaurant.getId()));
        userElem.appendChild(getNodeElement(doc, "Type", "restaurant"));
        userElem.appendChild(getNodeElement(doc, "Name", restaurant.getName()));
        userElem.appendChild(getNodeElement(doc, "UserName", restaurant.getUsername()));
        userElem.appendChild(getNodeElement(doc, "Password", restaurant.getPassword()));
        userElem.appendChild(getNodeElement(doc, "Address", restaurant.getAddress()));

        Element node = doc.createElement("Orders");
        if(!restaurant.getOrderHistory().isEmpty()){
            for(Order order:restaurant.getOrderHistory()){
                node.appendChild(toOrderXML(order,doc));
            }
        }
        Element menuNode = doc.createElement("Menus");
        if(!restaurant.getMenu().isEmpty()){
            for(Menu menu:restaurant.getMenu()){
                menuNode.appendChild(toMenuXML(menu,doc));
            }
        }
        userElem.appendChild(node);
        userElem.appendChild(menuNode);
        return userElem;
    }
    private static Node getNodeElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    private static Node toOrderXML(Order order, Document doc){
        Element orderElem = doc.createElement("Order");
        //userElem.setAttribute("id", String.valueOf(user.getId()));
        orderElem.setAttribute("id",String.valueOf(order.getId()));
        orderElem.appendChild(getNodeElement(doc, "CustomerName", order.getCustomerName()));
        orderElem.appendChild(getNodeElement(doc, "RestaurantName", order.getRestaurantName()));
        orderElem.appendChild(getNodeElement(doc, "Address", order.getAddress()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        orderElem.appendChild(getNodeElement(doc, "Date", dateFormat.format(order.getOrderDate())));
        Element node = doc.createElement("Foods");
        int foodId = 1;
        if(!order.getItems().isEmpty()){
            for(FoodCostPair food:order.getItems()){
                node.appendChild(toIFoodXML(foodId,food,doc));
                foodId++;
            }
        }
        orderElem.appendChild(node);
        return orderElem;
    }
    private static Node toIFoodXML(int id,FoodCostPair food, Document doc){
        Element foodElem = doc.createElement("Food");
        //userElem.setAttribute("id", String.valueOf(user.getId()));
        foodElem.setAttribute("id",String.valueOf(id));

        foodElem.appendChild(getNodeElement(doc, "Cost", String.valueOf(food.getCost())));
        foodElem.appendChild(getNodeElement(doc, "Food", food.getFood()));
        return foodElem;
    }
    private static Node toMenuXML(Menu menu, Document doc){
        Element menuElem = doc.createElement("Menu");
        menuElem.setAttribute("id", String.valueOf(menu.getId()));
        menuElem.appendChild(getNodeElement(doc, "Name", menu.getName()));
        int elemCounter=0;
        Element itemsNode = doc.createElement("Items");
        if(!menu.getItems().isEmpty()){
            for(Map.Entry<String, List<String>> item:menu.getItems().entrySet()){
                Element node = doc.createElement("Item");
                node.setAttribute("id",String.valueOf(elemCounter));
                elemCounter++;
                node.appendChild(getNodeElement(doc,"Name",item.getKey()));
                String ingredients = "";
                for(String ingredient:item.getValue()){
                    ingredients += ingredient + " ";
                }
                if(ingredients.length() > 0) {
                    ingredients = ingredients.substring(0, ingredients.length() - 1);
                }
                node.appendChild(getNodeElement(doc,"Ingredients",ingredients));
                itemsNode.appendChild(node);
            }
            menuElem.appendChild(itemsNode);
        }
        return menuElem;
    }
}
