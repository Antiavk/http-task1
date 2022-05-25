import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataRequest {
    @JsonProperty("id")
    public String id;
    @JsonProperty("text")
    public String text;
    @JsonProperty("type")
    public String type;
    @JsonProperty("user")
    public String user;
    @JsonProperty("upvotes")
    public Integer upvotes;

}
