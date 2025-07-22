package com.tecdesoftware.market_app.web.controller;

import com.tecdesoftware.market_app.Controller.domain.Purchase;
import com.tecdesoftware.market_app.domain.Service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market_app_purchase")
@Tag(name = "Purchase Controller", description = " Manages purchases in the store")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/purchases")
    @Operation(summary = "Get all purchases", description = "Retrieves a list of all purchases made in the store")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of purchases")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/purchases/{idCliente}")
    @Operation(summary = "Get purchases by user ID", description = "Retrieves a list of purchases made by a specific user")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of purchases by user ID")
    @ApiResponse(responseCode = "404", description = "No purchases found for the specified user ID")
    public ResponseEntity <List<Purchase>> getPurchasesByUserId(@PathVariable String idCliente) {
        return purchaseService.getPurchasesByUserId(idCliente)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/create-purchase")
    @Operation(summary = "Save a new product", description = "Saves a new product in the store",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            examples = @ExampleObject(
                                    name = "Product Example",
                                    value = """
                                    {
                                        "idCliente": "12345",
                                        "fecha": "2023-10-01T10:00:00Z",
                                        "productos": [
                                            {
                                                "idProducto": 1,
                                                "cantidad": 2
                                            },
                                            {
                                                "idProducto": 2,
                                                "cantidad": 1
                                            }
                                        ]

                                    }
                                    """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized access")
    @ApiResponse(responseCode = "403", description = "Forbidden access")
    @ApiResponse(responseCode = "409", description = " Conflict, product already exists")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

}
