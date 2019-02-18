//
//  CollectionViewController.swift
//  PictureCollection
//
//  Created by Lucas Dachman on 2/14/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

private let reuseIdentifier = "cell"

class CollectionViewController: UICollectionViewController, UICollectionViewDelegateFlowLayout {
    
    var imageNames = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Do any additional setup after loading the view.
        for i in 1...8 {
            imageNames.append("img\(i)")
        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using [segue destinationViewController].
        // Pass the selected object to the new view controller.
    }
    */

    // MARK: UICollectionViewDataSource

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of items
        return imageNames.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! CollectionViewCell
    
        // Configure the cell
        cell.imageView.image = UIImage(named: imageNames[indexPath.row])
    
        return cell
    }

    // MARK: UICollectionViewDelegate

    /*
    // Uncomment this method to specify if the specified item should be highlighted during tracking
    override func collectionView(_ collectionView: UICollectionView, shouldHighlightItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment this method to specify if the specified item should be selected
    override func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment these methods to specify if an action menu should be displayed for the specified item, and react to actions performed on the item
    override func collectionView(_ collectionView: UICollectionView, shouldShowMenuForItemAt indexPath: IndexPath) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, canPerformAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, performAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) {
    
    }
    */
    
    // MARK: UICollectionViewDelegateFlowLayout
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let image = UIImage(named: imageNames[indexPath.row])
        
        // code to create resized image from https://www.snip2code.com/Snippet/89236/Resize-Image-in-iOS-Swift
        //create new size
        let scale:CGFloat = 5
        let newSize = CGSize(width: (image?.size.width)!/scale, height:
            (image?.size.height)!/scale)
        return newSize
/*
        //creates a bitmap image with the new size
        UIGraphicsBeginImageContextWithOptions(newSize, false, 1.0)
        //draws the new image in a rectangle with the new size, scaling to fit
        let rect = CGRect(x: 0, y: 0, width: newSize.width, height:
            newSize.height)
        image?.draw(in: rect)
        //gets the image that was just drawn
        let image2 = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        //end resizing
        //returns size of resized image
//        return (image2?.size)!
 */
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        let sectionInsets = UIEdgeInsets(top: 25.0, left: 10.0, bottom: 25.0, right: 10.0)
        return sectionInsets
    }

    override func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        var header: HeaderCollectionReusableView?
        if kind == UICollectionView.elementKindSectionHeader {
            header =
                collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "header", for: indexPath) as? HeaderCollectionReusableView
            header?.headerLabel.text = "this ting i made"
        }
        return header!
    }
}
