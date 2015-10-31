package com.example.junt_t.mapdemo;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Junt_T on 2015/10/20 0020.
 */
public class ResponseSupermarketAPI {
    /**
     * Product : [{"Itemname":"Hersheys 2% Reduced Fat Chocolate Milk - 3-8 Fl. Oz.","ItemDescription":"Grade A. Vitamins A & D added. UHT. 1/3 Less fat than whole milk. Fat reduced from 8 g to 5 g per serving. Product of USA.","ItemCategory":"Beverages","ItemID":"27926","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/960013914_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Daisy 2% Milkfat Small Curd Cottage Cheese - 16 Oz","ItemDescription":"2% milkfat. Grade A.","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34030","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/136250031_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Daisy 2% Milkfat Small Curd Cottage Cheese - 32 Oz","ItemDescription":"2% Milkfat. Grade A.","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34031","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/960013858_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft American Singles 2 Percent Milk - 10.7 Oz","ItemDescription":"2% milk. Pasteurized prepared cheese product. 45% less fat than process cheese food. Made with 2% milk from cows not treated with the growth hormone rBST. The FDA has stated that there is no significant difference between milk from rBST-treated and untrea","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34160","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/960026963_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft Singles 2 Percent Milk - 14.7 Oz","ItemDescription":"2% milk. Made with 2% milk from cows not treated with the growth hormone rbST. The FDA has stated that there is no significant difference between milk from rbST treated and untreated cows. 45% less fat than process cheese food. Excellent source of calcium","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34171","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/960026396_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Lactaid 2% Reduced Fat Milk With Calcium - Half Gallon","ItemDescription":"100% lactose free. Easy to digest. Grade A. Vitamins A & D. Ultra-pasteurized & homogenized. 38% less fat than whole milk. Enjoy milk again! Taste goodness. Calcium Enriched: 20% more of the daily value of calcium than other milk. 2 glasses = 100% DV calc","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34392","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/136011343_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Lactaid Reduced Fat 2% Milk - Half Gallon","ItemDescription":"Enjoy milk again! Taste goodness. 100% lactose free. 38% less fat than whole milk. Easy to digest. Grade A. Vitamins A & D. Ultra-pasteurized. All of the goodness! None of the lactose. Did you know that Lactaid Milk is 100% real, farm-fresh milk with all ","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34395","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/136010104_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Horizon Organic 2% Reduced Fat Milk - 3-8 Oz","ItemDescription":"44% less fat than whole milk. Vitamins A & D added. UHT. Grade A. 2% milkfat. Our farms produced this milk without antibiotics, added hormones, pesticides or cloning. USDA organic. Single serve milk for people on the moo-ve! Milk in the pantry? You bet. T","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34413","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/960013853_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Horizon Organic Lf 2% Milk - Gallon","ItemDescription":"38% less fat than whole milk. Grade A. USDA organic. Our farms produced this milk without antibiotics, added hormones, pesticides or cloning. Vitamins A & D added. Pasteurized. Fat reduced from 8 g to 5 g per serving. Certified organic by Quality Assuranc","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34414","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/960017461_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Horizon Organic Reduced Fat 2% Milk Plus Dha - Half Gallon","ItemDescription":"Vitamin A & D added. Ultra-pasteurized. 38% less fat than whole milk. DHA omega-3 helps support a healthy brain. DHA omega-3 from 100% vegetarian algal oil. USDA Organic. Our farms produced this milk without antibiotics, added hormones, pesticides or clon","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34416","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/136050017_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Horizon Reduced Fat 2% Organic Milk - 64 Fl. Oz.","ItemDescription":"Vitamin A & D added. Ultra-pasteurized. 38% less fat than whole milk. USDA Organic. Our farms produced this milk without antibiotics, added hormones, pesticides or cloning. Fat reduced from 8 g to 5 g. Creating an organic connection. At Horizon Organic, w","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34417","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/136050015_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"O Organics Organic 2% Reduced Fat Milk - Gallon","ItemDescription":"USDA Organic. 38% Less fat than whole milk. Vitamins A & D added. 2% Milkfat. Grade A. Pasteurized. Homogenized. Fat reduced from 8 g to 5 g. Certified organic by Quality Assurance International. Product of U.S.A.","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34422","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/136050138_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"O Organics Reduced Fat 2% Milk - Half Gallon","ItemDescription":"2% Milkfat. 38% Less fat than whole milk. Vitamins A & D added. USDA organic. Grade A. Pasteurized. Homogenized. To be certified organic, dairy cows must be managed under organic livestock practices at least one year before milking. Their feed must be gro","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"34423","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/136010459_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft Velveeta Shells And Cheese 2% Milk Cups - 2.19 Oz","ItemDescription":"Microwaveable shell pasta & cheese sauce. Ready in 3-1/2 minutes. Made with 2% milk - 1/2 the fat of regular microwaveable shells & cheese. Fat per serving: Regular Microwaveable Shells & Cheese: 8 g. This Product: 3 g.","ItemCategory":"Grains, Pasta & Sides","ItemID":"38126","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/960026311_100x100.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Borden Dairy Cheese Product 2% Milk - 16 slices [10.67 oz (302 g)]","ItemDescription":"Borden Dairy Cheese Product 2% Milk - 16 slices [10.67 oz (302 g)]","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"72096","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/C9BE726.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Hersheys 2% Milkfat Real Homogenized Chocolate Milk - 3 - 8 fl oz (236 ml) boxes","ItemDescription":"Hersheys 2% Milkfat Real Homogenized Chocolate Milk - 3 - 8 fl oz (236 ml) boxes","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"73664","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/EEC9D2F.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft Natural Cheese Cheddar Mild 2% Milk Reduced Fat Chunk Cheese - 7 Oz Brick","ItemDescription":"Kraft Natural Cheese Cheddar Mild 2% Milk Reduced Fat Chunk Cheese - 7 Oz Brick","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"77053","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/6E1EE7D.gif","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft Natural Cheese Monterey Jack 2% Milk Reduced Fat Chunk Cheese - 7 Oz Brick","ItemDescription":"Kraft Natural Cheese Monterey Jack 2% Milk Reduced Fat Chunk Cheese - 7 Oz Brick","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"77054","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/5A37D5D.gif","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft Natural Cheese Monterey Jack 2% Milk Reduced Fat Chunk Cheese - 7 Oz Brick","ItemDescription":"Kraft Natural Cheese Monterey Jack 2% Milk Reduced Fat Chunk Cheese - 7 Oz Brick","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"77055","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/EC2F993.gif","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft Natural Cheese Mexican Style Four Cheese W/2% Milk Shredded Cheese - 7 Oz Peg","ItemDescription":"Kraft Natural Cheese Mexican Style Four Cheese W/2% Milk Shredded Cheese - 7 Oz Peg","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"77068","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/D9A5943.gif","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft Singles Cheddar Sharp 2% Milk 16 Ct Cheese Slices - 10.7 Oz Pack","ItemDescription":"Kraft Singles Cheddar Sharp 2% Milk 16 Ct Cheese Slices - 10.7 Oz Pack","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"77070","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/71F2C4B.gif","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft Velveeta w/2% Milk Cheese - 16 Oz Box","ItemDescription":"Kraft Velveeta w/2% Milk Cheese - 16 Oz Box","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"77104","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/D2D5B1C.gif","AisleNumber":"Aisle:N/A"},{"Itemname":"Kraft Dinners Deluxe w/Original Cheddar Cheese Sauce 2% Milk - 14 Oz Box","ItemDescription":"Kraft Dinners Deluxe w/Original Cheddar Cheese Sauce 2% Milk - 14 Oz Box","ItemCategory":"Grains, Pasta & Sides","ItemID":"77140","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/F22E63F.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Enfagrow Gentlease 2 Infant And Toddler Formula Milk-Based Powder W/Iron Enfagrow - 24 Oz Canister","ItemDescription":"Enfagrow Gentlease 2 Infant And Toddler Formula Milk-Based Powder W/Iron Enfagrow - 24 Oz Canister","ItemCategory":"Baby","ItemID":"78208","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/2706DBC.gif","AisleNumber":"Aisle:N/A"},{"Itemname":"Knudsen 100 Calorie Pineapple Small Curd 2% Milkfat Lowfat 4 Oz Cottage Cheese - 4 Pk Cups","ItemDescription":"Knudsen 100 Calorie Pineapple Small Curd 2% Milkfat Lowfat 4 Oz Cottage Cheese - 4 Pk Cups","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"81043","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/BA59F01.gif","AisleNumber":"Aisle:N/A"},{"Itemname":"Knudsen Small Curd Lowfat 2% Milkfat - 16 Oz Tub","ItemDescription":"Knudsen Small Curd Lowfat 2% Milkfat - 16 Oz Tub","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"81052","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/03D72E5.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Knudsen Small Curd Lowfat 2% Milkfat - 32 Oz Tub","ItemDescription":"Knudsen Small Curd Lowfat 2% Milkfat - 32 Oz Tub","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"81053","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/C6161A6.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Borden 2% Milk Singles American 16 3/4 Oz Slices - 12 Oz Package","ItemDescription":"Borden 2% Milk Singles American 16 3/4 Oz Slices - 12 Oz Package","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"81747","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/1669C7A.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Horizon Organic 2% Reduced Fat Milk - .5 Gal Carton","ItemDescription":"Horizon Organic 2% Reduced Fat Milk - .5 Gal Carton","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"83433","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/742A3DE.gif","AisleNumber":"Aisle:N/A"},{"Itemname":"Horizon Organic 2% Low Fat Real Organic Ultra Pasteurized White Milk - 3 - 8 fl oz (236 ml) cartons [24 fl oz]","ItemDescription":"Horizon Organic 2% Low Fat Real Organic Ultra Pasteurized White Milk - 3 - 8 fl oz (236 ml) cartons [24 fl oz]","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"91243","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/no_image_sm.jpg","AisleNumber":"Aisle:N/A"},{"Itemname":"Horizon Organic 2% Milkfat Real Organic Ultra Pstrzd Chocolate Milk - 3 - 8 fl oz cartons [24 fl oz]","ItemDescription":"Horizon Organic 2% Milkfat Real Organic Ultra Pstrzd Chocolate Milk - 3 - 8 fl oz cartons [24 fl oz]","ItemCategory":"Dairy, Eggs & Cheese","ItemID":"91244","ItemImage":"http://smapistorage.blob.core.windows.net/thumbimages/2/no_image_sm.jpg","AisleNumber":"Aisle:N/A"}]
     * _xmlns:xsi : http://www.w3.org/2001/XMLSchema-instance
     * _xmlns:xsd : http://www.w3.org/2001/XMLSchema
     * _xmlns : http://www.SupermarketAPI.com
     */

    private ArrayOfProductEntity ArrayOfProduct;

    public static ResponseSupermarketAPI objectFromData(String str) {

        return new Gson().fromJson(str, ResponseSupermarketAPI.class);
    }

    public void setArrayOfProduct(ArrayOfProductEntity ArrayOfProduct) {
        this.ArrayOfProduct = ArrayOfProduct;
    }

    public ArrayOfProductEntity getArrayOfProduct() {
        return ArrayOfProduct;
    }

    public static class ArrayOfProductEntity {
        /**
         * Itemname : Hersheys 2% Reduced Fat Chocolate Milk - 3-8 Fl. Oz.
         * ItemDescription : Grade A. Vitamins A & D added. UHT. 1/3 Less fat than whole milk. Fat reduced from 8 g to 5 g per serving. Product of USA.
         * ItemCategory : Beverages
         * ItemID : 27926
         * ItemImage : http://smapistorage.blob.core.windows.net/thumbimages/960013914_100x100.jpg
         * AisleNumber : Aisle:N/A
         */

        private List<ProductEntity> Product;

        public static ArrayOfProductEntity objectFromData(String str) {

            return new Gson().fromJson(str, ArrayOfProductEntity.class);
        }

        public void setProduct(List<ProductEntity> Product) {
            this.Product = Product;
        }

        public List<ProductEntity> getProduct() {
            return Product;
        }

        public static class ProductEntity {
            private String Itemname;
            private String ItemDescription;
            private String ItemCategory;
            private String ItemID;
            private String ItemImage;
            private String AisleNumber;

            public static ProductEntity objectFromData(String str) {

                return new Gson().fromJson(str, ProductEntity.class);
            }

            public void setItemname(String Itemname) {
                this.Itemname = Itemname;
            }

            public void setItemDescription(String ItemDescription) {
                this.ItemDescription = ItemDescription;
            }

            public void setItemCategory(String ItemCategory) {
                this.ItemCategory = ItemCategory;
            }

            public void setItemID(String ItemID) {
                this.ItemID = ItemID;
            }

            public void setItemImage(String ItemImage) {
                this.ItemImage = ItemImage;
            }

            public void setAisleNumber(String AisleNumber) {
                this.AisleNumber = AisleNumber;
            }

            public String getItemname() {
                return Itemname;
            }

            public String getItemDescription() {
                return ItemDescription;
            }

            public String getItemCategory() {
                return ItemCategory;
            }

            public String getItemID() {
                return ItemID;
            }

            public String getItemImage() {
                return ItemImage;
            }

            public String getAisleNumber() {
                return AisleNumber;
            }
        }
    }


//    private List<Product> product;
//
//    public void setProduct(List<Product> Product) {
//        this.product = Product;
//    }
//
//    public List<Product> getProduct() {
//        return product;
//    }
//    public static class Product {
//
//        private String Itemname;
//        private String ItemDescription;
//        private String ItemCategory;
//        private String ItemID;
//        private String ItemImage;
//        private String AisleNumber;
//
//        public void setItemname(String Itemname) {
//            this.Itemname = Itemname;
//        }
//
//        public void setItemDescription(String ItemDescription) {
//            this.ItemDescription = ItemDescription;
//        }
//
//        public void setItemCategory(String ItemCategory) {
//            this.ItemCategory = ItemCategory;
//        }
//
//        public void setItemID(String ItemID) {
//            this.ItemID = ItemID;
//        }
//
//        public void setItemImage(String ItemImage) {
//            this.ItemImage = ItemImage;
//        }
//
//        public void setAisleNumber(String AisleNumber) {
//            this.AisleNumber = AisleNumber;
//        }
//
//        public String getItemname() {
//            return Itemname;
//        }
//
//        public String getItemDescription() {
//            return ItemDescription;
//        }
//
//        public String getItemCategory() {
//            return ItemCategory;
//        }
//
//        public String getItemID() {
//            return ItemID;
//        }
//
//        public String getItemImage() {
//            return ItemImage;
//        }
//
//        public String getAisleNumber() {
//            return AisleNumber;
//        }
//    }

    /**
     * Store : {"Storename":"Luckys","Address":"247 E. 18th Street","City":"Oakland","State":"CA","Zip":"94606","Phone":"","StoreId":"e6k3fjw186k"}
     */

//    private StoreEntity Store;

//
//    public void setStore(StoreEntity Store) {
//        this.Store = Store;
//    }
//
//    public StoreEntity getStore() {
//        return Store;
//    }



//    public static class StoreEntity {
//        /**
//         * Storename : Luckys
//         * Address : 247 E. 18th Street
//         * City : Oakland
//         * State : CA
//         * Zip : 94606
//         * Phone :
//         * StoreId : e6k3fjw186k
//         */
//
//        private String Storename;
//        private String Address;
//        private String City;
//        private String State;
//        private String Zip;
//        private String StoreId;
//
//        public void setStorename(String Storename) {
//            this.Storename = Storename;
//        }
//
//        public void setAddress(String Address) {
//            this.Address = Address;
//        }
//
//        public void setCity(String City) {
//            this.City = City;
//        }
//
//        public void setState(String State) {
//            this.State = State;
//        }
//
//        public void setZip(String Zip) {
//            this.Zip = Zip;
//        }
//
//        public void setStoreId(String StoreId) {
//            this.StoreId = StoreId;
//        }
//
//        public String getStorename() {
//            return Storename;
//        }
//
//        public String getAddress() {
//            return Address;
//        }
//
//        public String getCity() {
//            return City;
//        }
//
//        public String getState() {
//            return State;
//        }
//
//        public String getZip() {
//            return Zip;
//        }
//
//        public String getStoreId() {
//            return StoreId;
//        }
//    }
}
