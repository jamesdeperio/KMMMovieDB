import SwiftUI
import shared

struct ContentView: View{
  
    var networkManager = NetworkManager()
    
    init() {
        stepCount()
    }
    var body: some View {
      
        Text("hello")
        
    }
    func stepCount() {
        var viewModel = HomeViewModel(restService: networkManager.restService, observer:Sample())
        
        
    }
    
}

class Sample: ViewStateObserver {
    func onObserve(i: Any?) {
        
    }
    
   
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
