package ca.senecacollege.dps924.assignment2_cashregister;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Item implements Parcelable {
    double amount;
    String product_type;
    int quantity;
    String purchaseDate;

    public Item(double amount, String product_type, int quantity, String purchaseDate) {
        this.amount = amount;
        this.product_type = product_type;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    protected Item(Parcel in) {
        this.amount = in.readDouble();
        this.product_type = in.readString();
        this.quantity = in.readInt();
        this.purchaseDate = in.readString();
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
        parcel.writeString(this.purchaseDate);
    }
}
