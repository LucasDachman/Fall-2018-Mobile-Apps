//
//  ThirdViewController.swift
//  music
//
//  Created by Lucas Dachman on 1/24/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class ThirdViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    
    @IBOutlet weak var musicPicker: UIPickerView!
    @IBOutlet weak var choiceLabel: UILabel!
    
    let ARTIST_COMPONENT = 0
    let ALBUM_COMPONENT = 1
    
    var artistAlbums = [ArtistAlbums]()
    var artists = [String]()
    var albums = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        musicPicker.delegate = self
        musicPicker.dataSource = self
        
        if let pathURL = Bundle.main.url(forResource: "artistalbums2", withExtension: "plist") {
            let plistDecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                artistAlbums = try plistDecoder.decode([ArtistAlbums].self, from: data)
                for artist in artistAlbums {
                    artists.append(artist.name)
                }
                albums = artistAlbums[0].albums
            } catch {
                print(error)
            }
        }
    }

    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        switch component {
        case ARTIST_COMPONENT:
            return artists.count
        case ALBUM_COMPONENT:
            return albums.count
        default:
            return 0
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        switch component {
        case ARTIST_COMPONENT:
            return artists[row]
        case ALBUM_COMPONENT:
            return albums[row]
        default:
            return ""
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == ARTIST_COMPONENT {
            albums = artistAlbums[row].albums
            musicPicker.reloadComponent(ALBUM_COMPONENT)
            musicPicker.selectRow(0, inComponent: ALBUM_COMPONENT, animated: true)
        }
        let artistChoice = artists[pickerView.selectedRow(inComponent: ARTIST_COMPONENT)]
        let albumChoice = albums[pickerView.selectedRow(inComponent: ALBUM_COMPONENT)]
        choiceLabel.text = "\(albumChoice) by \(artistChoice) is awesome!"
    }
    
}
