//
//  ImageEditor.swift
//  project-1
//
//  Created by Lucas Dachman on 3/9/19.
//  Adapted from demo: https://youtu.be/j3VtUKucYSw
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation
import CoreImage
import UIKit

struct Filter {
    let displayName: String
    let name: String
    var effectValue: Any?
    var effectValueName: String?
    
    init(displayName: String, name: String, effectValue: Any?, effectValueName: String?) {
        self.displayName = displayName
        self.name = name
        self.effectValue = effectValue
        self.effectValueName = effectValueName
    }
}

class ImageEditor {
    
    // orignal image and edited image should be same except for effects
    static var originalImage: UIImage? {
        willSet {
            guard let cgimage = newValue?.cgImage else {
                print("Error setting image: image is nil")
                return
            }
            editedImage = cgimage
        }
    }
    static var appliedFilters = [Filter]()
    
    private static var editedImage: CGImage?
    private static let ciContext = CIContext()
    
    
    private static func apply(_ filterAttrs: Filter) {
        guard let cgImage = editedImage else {
            print("Error applying filter. No image set")
            return
        }
        
        let ciImage = CIImage(cgImage: cgImage)
        
        // set filter name from list of string keys
        let filter = CIFilter(name: filterAttrs.name)
        
        // set input image
        filter?.setValue(ciImage, forKey: kCIInputImageKey)
        
        // check for extra value attributes
        if let effectValue = filterAttrs.effectValue, let effectName = filterAttrs.effectValueName {
            filter?.setValue(effectValue, forKey: effectName)
        }
        
        // render image
        if let output = filter?.value(forKey: kCIOutputImageKey) as? CIImage,
            let cgImageResult = ciContext.createCGImage(output, from: output.extent) {
            editedImage = cgImageResult
        }
    }
    
    static var filteredImage: UIImage? {
        
        // reset image
        editedImage = originalImage?.cgImage
        
        // apply all the filters sequentially
        for filter in appliedFilters {
            apply(filter)
        }
        
        // check that image editing succeeded
        guard let finalImage = editedImage else {
            print("Error getting filteredImage: editedImage is nil")
            return nil
        }
        
        return UIImage(cgImage: finalImage)
    }
    
    static let allFilters: [Filter] = [
        Filter(displayName: "Sepia", name: "CISepiaTone", effectValue: 0.75, effectValueName: kCIInputIntensityKey),
        Filter(displayName: "Noir", name: "CIPhotoEffectNoir", effectValue: nil, effectValueName: nil),
        Filter(displayName: "Vignette", name: "CIVignette", effectValue: nil, effectValueName: nil),
        Filter(displayName: "Cool Vintage", name: "CIPhotoEffectProcess", effectValue: nil, effectValueName: nil),
        Filter(displayName: "Chrome", name: "CIPhotoEffectChrome", effectValue: nil, effectValueName: nil),
        Filter(displayName: "Warm Vintage", name: "CIPhotoEffectTransfer", effectValue: nil, effectValueName: nil)
    ]
    
}
