package ca.senecacollege.dps924.assignment2_cashregister;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    double amount;
    String product_type;
    int quantity;

    public Item(double amount, String product_type, int quantity) {
        this.amount = amount;
        this.product_type = product_type;
        this.quantity = quantity;
    }

    protected Item(Parcel in) {
        this.amount = in.readDouble();
        this.product_type = in.readString();
        this.quantity = in.readInt();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.amount);
        parcel.writeString(this.product_type);
        parcel.writeInt(this.quantity);
    }
}
