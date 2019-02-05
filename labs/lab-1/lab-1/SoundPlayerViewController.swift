//
//  SecondViewController.swift
//  lab-1
//
//  Created by Lucas Dachman on 2/4/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit
import AVFoundation

class SoundPlayerViewController: UIViewController {
    @IBOutlet weak var button1: UIButton!
    @IBOutlet weak var button2: UIButton!
    @IBOutlet weak var button3: UIButton!
    @IBOutlet weak var button4: UIButton!
    var buttons = [UIButton]()
    
    var soundNames: [String] = ChosenSounds.getSounds()
    var players = [AVAudioPlayer]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        buttons = [button1, button2, button3, button4]
    }
    
    override func viewWillAppear(_ animated: Bool) {
        loadSounds()
        for button in buttons {
            button.layer.borderWidth = 1
        }
    }
    
    func loadSounds() {
        soundNames = ChosenSounds.getSounds()
        for i in 0..<soundNames.count {
            if let url = Bundle.main.url(forResource: soundNames[i], withExtension: "wav") {
                do {
                    let player = try AVAudioPlayer(contentsOf: url)
                    players.append(player)
                    player.prepareToPlay()
                    let title = soundNames[i].components(separatedBy: "/").last
                    buttons[i].setTitle(title, for: .normal)
                } catch {
                    print(error)
                }
            } else {
                print("cant find", soundNames[i])
            }
        }
    }
    
    @IBAction func onTouch(_ sender: UIButton) {
        switch sender.tag {
        case 0:
            players[0].play()
        case 1:
            players[1].play()
        case 2:
            players[2].play()
        case 3:
            players[3].play()
        default:
            print("no tag: \(sender.tag)")
        }
    }
    
    

}

