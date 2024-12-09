package base_test.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    public enum Color {
        BLACK,
        GREY,
        BLACK_OR_GREY
    }

    public void setColor(String color){
        switch (color) {
            case "BLACK":
                this.color = new String[] {"BLACK"};
                break;
            case "GREY":
                this.color = new String[] {"GREY"};
                break;
            case "BLACK_OR_GREY":
                this.color = new String[] {"BLACK", "GREY"};
                break;
            default:
                this.color = new String[] {};
                break;
        }
    }
}
