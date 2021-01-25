package com.example.itemtracker;

public class ReportItemModel {
        private String name;
        private String id;
        private boolean isMissing;
        private boolean isBroken;
        private String details;

        //Constructors
        public ReportItemModel(String name, String id, boolean isMissing, boolean isBroken, String details) {
                this.name = name;
                this.id = id;
                this.isMissing = isMissing;
                this.isBroken = isBroken;
                this.details = details;
        }
        //toString method

        @Override
        public String toString() {
                if(isMissing) {
                        return
                                "Name = " + name + ", ID = " + id + ", is Missing";
                }
                else{
                        return
                                "Name = " + name + ", ID = " + id + ", is Broken";
                }
        }

        public ReportItemModel() {
        }

        //Getters and Setters
        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public boolean isMissing() {
                return isMissing;
        }

        public void setMissing(boolean missing) {
                isMissing = missing;
        }

        public boolean isBroken() {
                return isBroken;
        }

        public void setBroken(boolean broken) {
                isBroken = broken;
        }

        public String getDetails() {
                return details;
        }

        public void setDetails(String details) {
                this.details = details;
        }
}

