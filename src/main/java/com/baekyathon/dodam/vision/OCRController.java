//package com.baekyathon.dodam.vision;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class OCRController {
//
//    @Autowired
//    private VisionService visionService;
//
//    @GetMapping("/extract-text")
//    public String extractText(@RequestParam("imageUrl") String imageUrl) {
//        try {
//            return visionService.extractTextFromImageUrl(imageUrl);
//        } catch (Exception e) {
//            //e.printStackTrace();
//            return "Failed to extract text: " + e.getMessage();
//        }
//    }
//}
