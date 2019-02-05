//
//  FourthViewController.swift
//  music
//
//  Created by Lucas Dachman on 1/29/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class FourthViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func goToMusic(_ sender: UIButton) {
        // check for apps
        if (UIApplication.shared.canOpenURL(URL(string: "spotify://")!)) {
            UIApplication.shared.open(URL(string: "spotify://")!, options: [:], completionHandler: nil)
        }
        else if(UIApplication.shared.canOpenURL(URL(string: "music://")!)){
            UIApplication.shared.open(URL(string: "music://")!, options:
                [:], completionHandler: nil)
        } else {
            UIApplication.shared.open(URL(string:
                "http://www.apple.com/music/")!, options: [:], completionHandler: nil)
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
