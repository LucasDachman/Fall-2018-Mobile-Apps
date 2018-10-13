//
//  ViewController.swift
//  lab-4
//
//  Created by Lucas Dachman on 10/12/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet var mainView: UIView!
    @IBOutlet weak var textView: UITextView!
    var theme = Theme()
    
    let fileName = "saved.plist"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let fileURL = dataFileURL(fileName)
        if FileManager.default.fileExists(atPath: (fileURL?.path)!) {
            let url = fileURL!
            do {
                let data = try Data(contentsOf: url)
                let decoder = PropertyListDecoder()
                theme = try decoder.decode(Theme.self, from: data)
            }
            
        }
        
        setupTheme()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func unwindSegue(_ segue: UIStoryboardSegue) {
        setupTheme()
    }
    
    func setupTheme() {
        mainView.backgroundColor = theme.bgColor
        textView.backgroundColor = theme.bgColor
        textView.text = theme.text
        textView.textColor = theme.textColor
        if let size = theme.textSize {
            textView.font = textView.font?.withSize(CGFloat(size))
        } else {
            textView.font = textView.font?.withSize(CGFloat(theme.defaultSize))
        }
    }
    
    func dataFileURL(_ filename: String) -> URL? {
        let urls = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let url = urls.first?.appendingPathComponent(filename)
        return url
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "editTheme" {
            let editView = segue.destination as! ThemeEditViewController
            editView.theme = self.theme
        }
    }

}

