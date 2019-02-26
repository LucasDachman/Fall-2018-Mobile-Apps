//
//  detailViewController.swift
//  lab-3
//
//  Created by Lucas Dachman on 2/25/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class detailViewController: UIViewController {
    @IBOutlet weak var imageView: UIImageView!
    var imageName: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .action, target: self, action: #selector (share))
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let imageName = imageName {
            imageView.image = UIImage(named: imageName)
        }
    }

    @objc func share() {
        var imageArray = [UIImage]()
        imageArray.append(imageView.image!)
        let shareScreen = UIActivityViewController(activityItems: imageArray, applicationActivities: nil)
        shareScreen.modalPresentationStyle = .popover
        shareScreen.popoverPresentationController?.barButtonItem = navigationItem.rightBarButtonItem
        present(shareScreen, animated: true, completion: nil)
    }
    
}
