package com.example.jobala.apply;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApplyRequest {

    @AllArgsConstructor
    @Data
    public class PassFailDTO {
        private Integer id;
        private Boolean state;

        public String getPassFailStatus(){
            try {
                if (this.state) return "합격";
                else if (!this.state) return "불합격";
                else return null;
            } catch (Exception e) {
                return null;
            }
        }
    }
}