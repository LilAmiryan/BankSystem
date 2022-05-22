package response.address;

import com.example.banksystem.dto.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AddressDeleteResponse {
    private AddressDto addressDto;

    public AddressDeleteResponse() {
    }

    public AddressDeleteResponse(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public ResponseEntity<?> onFailure(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No address with such id.");
    }

    public ResponseEntity<AddressDto> onSuccess(){
        return ResponseEntity.ok().body(addressDto);
    }
}
