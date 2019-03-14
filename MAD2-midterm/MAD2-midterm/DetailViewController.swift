//
//  DetailViewController.swift
//  MAD2-midterm
//
//  Created by Lucas Dachman on 3/14/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit
import WebKit

class DetailViewController: UIViewController, WKUIDelegate, WKNavigationDelegate {

    @IBOutlet weak var webView: WKWebView!
    @IBOutlet weak var webSpinner: UIActivityIndicatorView!
    
    var movie: Movie?
    
    func webView(_ webView: WKWebView, didStartProvisionalNavigation navigation: WKNavigation!) {
        webSpinner.startAnimating()
    }
    
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        webSpinner.stopAnimating()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        webSpinner.hidesWhenStopped = true
        webView.uiDelegate = self
        webView.navigationDelegate = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let title = movie?.name {
            navigationItem.title = title
        }
        DispatchQueue.main.async {self.loadURL()}
    }
    
    func loadURL() {
        guard let urlPath = movie?.url,
            let url = URL(string: urlPath)
            else {
                print("could not evaluate url")
                navigationItem.title = "Failed"
                return
        }
        
        let req = URLRequest(url: url)
        webView.load(req)
    }
}

