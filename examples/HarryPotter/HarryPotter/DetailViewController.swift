//
//  DetailViewController.swift
//  HarryPotter
//
//  Created by Lucas Dachman on 2/19/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit
import WebKit

class DetailViewController: UIViewController , WKUIDelegate {

    @IBOutlet weak var detailDescriptionLabel: UILabel!
    @IBOutlet weak var webView: WKWebView!
    var character: Character?

    
    func configureView() {
        // Update the user interface for the detail item.
        if let character = character {
            if let label = detailDescriptionLabel {
                label.text = character.name
                loadWebPage(character.url)
            }
        }
    }
    
    func loadWebPage(_ path: String) {
        //the urlString should be a propery formed url
        //creates a URL object
        let url = URL(string: path)
        //create a URLRequest object
        let request = URLRequest(url: url!)
        //load the URLRequest obj
        webView.load(request)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        configureView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        configureView()
    }



}

