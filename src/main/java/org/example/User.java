package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;


public class User {
    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Login login;
    private DateOfBirth dob;
    private Registered registered;
    private String phone;
    private String cell;
    private ID id;
    private Picture picture;
    private String nat;


    @Override
    public String toString() {
        return "User {\n" +
                "  gender='" + gender + "',\n" +
                "  name=" + name + ",\n" +
                "  location=" + location + ",\n" +
                "  email='" + email + "',\n" +
                "  login=" + login + ",\n" +
                "  dob=" + dob + ",\n" +
                "  registered=" + registered + ",\n" +
                "  phone='" + phone + "',\n" +
                "  cell='" + cell + "',\n" +
                "  id=" + id + ",\n" +
                "  picture=" + picture + ",\n" +
                "  nat='" + nat + "'\n" +
                "}\n\n";
    }


    public String getGender() {
        return gender;
    }
    public Name getName() {
        return name;
    }
    public Location getLocation() {
        return location;
    }
    public String getEmail() {
        return email;
    }
    public Login getLogin() {
        return login;
    }
    public DateOfBirth getDob() {
        return dob;
    }
    public Registered getRegistered() {
        return registered;
    }
    public String getPhone() {
        return phone;
    }
    public String getCell() {
        return cell;
    }
    public ID getId() {
        return id;
    }
    public Picture getPicture() {
        return picture;
    }
    public String getNat() {
        return nat;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setName(Name name) {
        this.name = name;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
    public void setDob(DateOfBirth dob) {
        this.dob = dob;
    }
    public void setRegistered(Registered registered) {
        this.registered = registered;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setCell(String cell) {
        this.cell = cell;
    }
    public void setId(ID id) {
        this.id = id;
    }
    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    public void setNat(String nat) {
        this.nat = nat;
    }

    public static class Name {
        private String title;
        private String first;
        private String last;

        @Override
        public String toString() {
            return "Name {\n" +
                    "  title='" + title + "',\n" +
                    "  first='" + first + "',\n" +
                    "  last='" + last + "'\n" +
                    '}';
        }


        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getFirst() {
            return first;
        }
        public void setFirst(String first) {
            this.first = first;
        }
        public String getLast() {
            return last;
        }
        public void setLast(String last) {
            this.last = last;
        }
    }

    public static class Location {
        private Street street;
        private String city;
        private String state;
        private String country;
        private String postcode;
        private Coordinates coordinates;
        private Timezone timezone;

        @Override
        public String toString() {
            return "Location {\n" +
                    "  street=" + street + ",\n" +
                    "  city='" + city + "',\n" +
                    "  state='" + state + "',\n" +
                    "  country='" + country + "',\n" +
                    "  postcode='" + postcode + "',\n" +
                    "  coordinates=" + coordinates + ",\n" +
                    "  timezone=" + timezone + "\n" +
                    '}';
        }


        public Street getStreet() {
            return street;
        }
        public void setStreet(Street street) {
            this.street = street;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public String getPostcode() {
            return postcode;
        }
        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }
        public Coordinates getCoordinates() {
            return coordinates;
        }
        public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
        }
        public Timezone getTimezone() {
            return timezone;
        }
        public void setTimezone(Timezone timezone) {
            this.timezone = timezone;
        }

        public static class Street {
            private int number;
            private String name;

            @Override
            public String toString() {
                return "Street {\n" +
                        "  number=" + number + ",\n" +
                        "  name='" + name + "'\n" +
                        '}';
            }


            public int getNumber() {
                return number;
            }
            public void setNumber(int number) {
                this.number = number;
            }
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }
        }

        public static class Coordinates {
            private String latitude;
            private String longitude;

            @Override
            public String toString() {
                return "Coordinates {\n" +
                        "  latitude='" + latitude + "',\n" +
                        "  longitude='" + longitude + "'\n" +
                        '}';
            }


            public String getLatitude() {
                return latitude;
            }
            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }
            public String getLongitude() {
                return longitude;
            }
            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }
        }

        public static class Timezone {
            private String offset;
            private String description;

            @Override
            public String toString() {
                return "Timezone {\n" +
                        "  offset='" + offset + "',\n" +
                        "  description='" + description + "'\n" +
                        '}';
            }


            public String getOffset() {
                return offset;
            }
            public void setOffset(String offset) {
                this.offset = offset;
            }
            public String getDescription() {
                return description;
            }
            public void setDescription(String description) {
                this.description = description;
            }
        }
    }

    public static class Login {
        private String uuid;
        private String username;
        private String password;
        private String salt;
        private String md5;
        private String sha1;
        private String sha256;

        @Override
        public String toString() {
            return "Login {\n" +
                    "  uuid='" + uuid + "',\n" +
                    "  username='" + username + "',\n" +
                    "  password='" + password + "',\n" +
                    "  salt='" + salt + "',\n" +
                    "  md5='" + md5 + "',\n" +
                    "  sha1='" + sha1 + "',\n" +
                    "  sha256='" + sha256 + "'\n" +
                    '}';
        }


        public String getUuid() {
            return uuid;
        }
        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getSalt() {
            return salt;
        }
        public void setSalt(String salt) {
            this.salt = salt;
        }
        public String getMd5() {
            return md5;
        }
        public void setMd5(String md5) {
            this.md5 = md5;
        }
        public String getSha1() {
            return sha1;
        }
        public void setSha1(String sha1) {
            this.sha1 = sha1;
        }
        public String getSha256() {
            return sha256;
        }
        public void setSha256(String sha256) {
            this.sha256 = sha256;
        }
    }

    public static class DateOfBirth {
        private Timestamp date;
        private int age;

        @Override
        public String toString() {
            return "DateOfBirth {\n" +
                    "  date='" + date + "',\n" +
                    "  age=" + age + "\n" +
                    '}';
        }


        public Timestamp getDate() {
            return date;
        }
        public void setDate(Timestamp  date) {
            this.date = date;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class Registered {
        private Timestamp date;
        private int age;

        @Override
        public String toString() {
            return "Registered {\n" +
                    "  date='" + date + "',\n" +
                    "  age=" + age + "\n" +
                    '}';
        }


        public Timestamp getDate() {
            return date;
        }
        public void setDate(Timestamp date) {
            this.date = date;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class ID {
        private String name;
        private String value;

        @Override
        public String toString() {
            return "ID {\n" +
                    "  name='" + name + "',\n" +
                    "  value='" + value + "'\n" +
                    '}';
        }


        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class Picture {
        private String large;
        private String medium;
        private String thumbnail;
        private byte[] largeBytes;
        private byte[] mediumBytes;
        private byte[] thumbnailBytes;
        HttpService httpService;

        public Picture(){
            httpService = new HttpService();
        }
        @Override
        public String toString() {
            try {

                BufferedImage largeImage;
                BufferedImage mediumImage;
                BufferedImage thumbnailImage;
                largeImage = ImageIO.read(new ByteArrayInputStream(largeBytes));
                mediumImage = ImageIO.read(new ByteArrayInputStream(mediumBytes));
                thumbnailImage = ImageIO.read(new ByteArrayInputStream(thumbnailBytes));
                return "Picture {\n" +
                        "  largePicture='" + largeImage.getHeight() + "x" + largeImage.getWidth()+ "',\n" +
                        "  mediumPicture='" + mediumImage.getHeight() + "x" + mediumImage.getWidth() + "',\n" +
                        "  thumbnailPicture='" + thumbnailImage.getHeight() + "x" + thumbnailImage.getWidth() + "'\n" +
                        "}" ;


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        public String getLarge() {
            return large;
        }
        public void setLarge(String large) {
            this.large = large;
        }
        public String getMedium() {
            return medium;
        }
        public void setMedium(String medium) {
            this.medium = medium;
        }
        public String getThumbnail() {
            return thumbnail;
        }
        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
        public byte[] getLargeBytes() {
            return largeBytes;
        }
        public void setLargeBytes(byte[] largeBytes) {
            this.largeBytes = largeBytes;
        }
        public byte[] getMediumBytes() {
            return mediumBytes;
        }
        public void setMediumBytes(byte[] mediumBytes) {
            this.mediumBytes = mediumBytes;
        }
        public byte[] getThumbnailBytes() {
            return thumbnailBytes;
        }
        public void setThumbnailBytes(byte[] thumbnailBytes) {
            this.thumbnailBytes = thumbnailBytes;
        }
        public void setImagesInBytes(byte[] thumbnailBytes, byte[] mediumBytes, byte[] largeBytes) {
            this.largeBytes = largeBytes;
            this.mediumBytes = mediumBytes;
            this.thumbnailBytes = thumbnailBytes;
        }
        public void loadImage(){
            largeBytes =  httpService.getImageBytes(large);
            mediumBytes =  httpService.getImageBytes(medium);
            thumbnailBytes =  httpService.getImageBytes(thumbnail);
        }

    }
}
