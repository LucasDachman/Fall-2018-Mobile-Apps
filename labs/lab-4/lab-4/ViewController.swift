//
//  ViewController.swift
//  lab-4
//
//  Created by Lucas Dachman on 3/4/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var descLabel: UILabel!
    var apod: APOD?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        loadjson()
    }

    func loadjson(){
        let urlPath = "https://api.nasa.gov/planetary/apod?api_key=bAkFAhEuczMBGt6gs6JONbmfKH1INpdfsjTpghGi"
        guard let url = URL(string: urlPath) else {
            print("url error")
            return
        }
        let session = URLSession.shared.dataTask(with: url, completionHandler: {(data, response, error) in
            
            let httpResponse = response as! HTTPURLResponse
            let statusCode = httpResponse.statusCode
            guard statusCode == 200 else {
                print("file download error")
                return
            }
            //download successful
            print("download complete")
            guard let data = data else {
                return
            }
            print(data)
            DispatchQueue.main.async {self.parsejson(data)}
        })
        //must call resume to run session
        session.resume()
    }
    
    func parsejson(_ data: Data) {
        do {
            apod = try JSONDecoder().decode(APOD.self, from: data)
            // reload some data here
            updateView()
        }
        catch let err {
            print(err.localizedDescription)
        }
    }
    
    func updateView() {
        guard let apod = apod else {
            print("error!")
            return
        }
        let url = URL(string: apod.url)!
        let imageData = try? Data(contentsOf: url)
        if let image = imageData {
            imageView.image = UIImage(data: image)
        }
        titleLabel.text = apod.title
        descLabel.text = apod.explanation
        
    }

}

