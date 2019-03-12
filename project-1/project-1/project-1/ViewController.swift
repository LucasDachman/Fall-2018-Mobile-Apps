//
//  ViewController.swift
//  project-1
//
//  Created by Lucas Dachman on 3/8/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit
import CoreImage

class ViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    let imagePicker = UIImagePickerController()
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var editButton: UIBarButtonItem!
    @IBOutlet weak var saveButton: UIBarButtonItem!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        imagePicker.delegate = self
        imagePicker.sourceType = .photoLibrary
        imagePicker.allowsEditing = true
        
//        editButton.isEnabled = false
//        saveButton.isEnabled = false
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let filteredImage = ImageEditor.filteredImage {
            imageView.image = filteredImage
        }
    }
    
    @IBAction func onChooseTapped(_ sender: UIBarButtonItem) {
        self.present(imagePicker, animated: true, completion: nil)
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        let key = UIImagePickerController.InfoKey.originalImage
        guard let selectedImage = info[key] as? UIImage else {
            print("Error getting selected image: selected image is nil")
            return
        }
        ImageEditor.originalImage = selectedImage
        imageView.image = selectedImage
        editButton.isEnabled = true
        saveButton.isEnabled = true
        imagePicker.dismiss(animated: true, completion: nil)
    }
}

