package com.locally.locallyoffersbff;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class LocallyController {

    @GetMapping("/offers/{id}")
    public Offer returnOffer(@PathVariable("id") String id) {
        return new Offer(id);
    }

    public static class Category {
        private String id = "2";
        private String name = "Bluzy";

        public Category(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }


    public static class Seller {
        private String id = "2";
        private String name = "Iza Pracz";
        private boolean is_online = true;
        private String phone = "222 222 222";

        public Seller(String id, String name, boolean is_online, String phone) {
            this.id = id;
            this.name = name;
            this.is_online = is_online;
            this.phone = phone;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean isIs_online() {
            return is_online;
        }

        public String getPhone() {
            return phone;
        }
    }

    public static class Location {
        private  String name = "Toruń";
        private  String latitude =  "53.01379";
        private  String longitude = "18.598444";

        public Location(String name, String latitude, String longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }
    }

    public static class Offer {
        private final String id;
        private final String status;
        private final String title;
        private final String description;
        private final Location location;
        private final String images;
        private final String price;
        private final Seller seller;
        private final Category category;
        private final Instant added_at;
        private final Instant valid_to;
        private final String views;


        public Offer(String id) {
            this.id = id;
            this.status = null;
            this.title = null;
            this.description = null;
            this.location = null;
            this.images = null;
            this.price = null;
            this.seller = null;
            this.category = null;
            this.added_at = null;
            this.valid_to = null;
            this.views = null;
        }

        public Offer(String id, String status, String title, String description, Location location, String images, String price, Seller seller, Category category, Instant added_at, Instant valid_to, String views) {
            this.id = id;
            this.status = status;
            this.title = title;
            this.description = description;
            this.location = location;
            this.images = images;
            this.price = price;
            this.seller = seller;
            this.category = category;
            this.added_at = added_at;
            this.valid_to = valid_to;
            this.views = views;
        }

        public String getId() {
            return id;
        }

        public String getStatus() {
            return status;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Location getLocation() {
            return location;
        }

        public String getImages() {
            return images;
        }

        public String getPrice() {
            return price;
        }

        public Seller getSeller() {
            return seller;
        }

        public Category getCategory() {
            return category;
        }

        public Instant getAdded_at() {
            return added_at;
        }

        public Instant getValid_to() {
            return valid_to;
        }

        public String getViews() {
            return views;
        }
    }


}
