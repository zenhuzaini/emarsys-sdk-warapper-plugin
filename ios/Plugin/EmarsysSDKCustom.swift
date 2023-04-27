import Foundation

@objc public class EmarsysSDKCustom: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
