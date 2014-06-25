package de.pizzeria.gargano;

import android.os.Parcel;
import android.os.Parcelable;

public class PizzaModel implements Parcelable {
	
	private String name;
	private String price;
	private String description;
	private String id;
	
	public PizzaModel( String names, String price, String description, String id){
		setPizzaName(names);
		setPizzaPrice(price);
		setPizzaDescriptions(description);
		setPizzaId(id);
	}

	public void setPizzaId(String id) {
		this.id = id;
	}
	
	public String getPizzaId(){
		return this.id;
	}

	public String getPizzaName() {
		return name;
	}

	public void setPizzaName(String pizzaNames) {
		this.name = pizzaNames;
	}

	public String getPizzaPrice() {
		return price;
	}

	public void setPizzaPrice(String pizzaPrice) {
		this.price = pizzaPrice;
	}

	public String getPizzaDescriptions() {
		return description;
	}

	public void setPizzaDescriptions(String pizzaDescriptions) {
		this.description = pizzaDescriptions;
	}

    protected PizzaModel(Parcel in) {
        name = in.readString();
        price = in.readString();
        description = in.readString();
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(description);
        dest.writeString(id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PizzaModel> CREATOR = new Parcelable.Creator<PizzaModel>() {
        @Override
        public PizzaModel createFromParcel(Parcel in) {
            return new PizzaModel(in);
        }

        @Override
        public PizzaModel[] newArray(int size) {
            return new PizzaModel[size];
        }
    };
}