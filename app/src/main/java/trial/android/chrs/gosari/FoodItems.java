package trial.android.chrs.gosari;

/**
 * Created by greg on 4/25/16.
 */
public class FoodItems {


    private String pk;
    private String pk_categories;
    private String items;
    private String price;
    private String description;
    private String count;
    private String archived;

    FoodItems(){

    }

    public FoodItems(String pk,String pk_categories,String items,String price,String description,String count,String archived){
        super();
        this.pk=pk;
        this.pk_categories=pk_categories;
        this.items=items;
        this.price=price;
        this.description=description;
        this.count=count;
        this.archived=archived;
    }


    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getPk_categories() {
        return pk_categories;
    }

    public void setPk_categories(String pk_categories) {
        this.pk_categories = pk_categories;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArchived() {
        return archived;
    }

    public void setArchived(String archived) {
        this.archived = archived;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }



}
