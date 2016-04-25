package trial.android.chrs.gosari;

/**
 * Created by greg on 4/23/16.
 */
public class ItemCategory {

    private String pk;
    private String category;
    private String description;
    private String archived;


    public ItemCategory(){

    }
    public ItemCategory(String pk, String category,String archived){
        super();
        this.pk=pk;
        this.category=category;
        this.archived=archived;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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


}
