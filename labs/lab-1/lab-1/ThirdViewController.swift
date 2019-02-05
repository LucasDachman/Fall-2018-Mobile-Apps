//
//  ThirdViewController.swift
//  lab-1
//
//  Created by Lucas Dachman on 2/5/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class ThirdViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func buttonPressed(_ sender: Any) {
        goToBrowser()
    }
    
    
    func goToBrowser() {
        if (UIApplication.shared.canOpenURL(URL(string: "googlechrome://")!)) {
            UIApplication.shared.open(URL(string: "googlechrome://www.youtube.com")!, options: [:], completionHandler: nil)
        }
        else {
            UIApplication.shared.open(URL(string: "www.youtube.com")!, options: [:], completionHandler: nil)
        }
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
