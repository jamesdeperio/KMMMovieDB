import SwiftUI
import shared

struct ContentView: View{
  

    @ObservedObject var viewModel = Sample()
    
    init() {
        stepCount()
    }
    var body: some View {
      
        VStack {
                   Button(action: {
                    viewModel.registerRequest()
                   }) {
                       Text("SignUp")
                   }
                   Text(viewModel.textToUpdate)
               }
        
    }
     func stepCount() {
        
    
        
    }
    
}

class Sample: ViewStateObserver,ObservableObject {
    @Published var textToUpdate: String = "Update me!"
    func onObserve(i: Any?) {
        print(i)
        if (i is HomeState.LoadPopularItem) {
            textToUpdate = i as! String
        }
    }
    func registerRequest(){
        let networkManager = NetworkManager()
        let hvm = HomeViewModel(restService: networkManager.restService, observer:self)
        
        hvm.loadPopularTV()
      
    }
    
   
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
