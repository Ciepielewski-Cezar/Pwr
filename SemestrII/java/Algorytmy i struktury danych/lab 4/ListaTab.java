
    public class ListaTab implements Queue {
        private final ListaWiazana _list;


        public ListaTab() {

            this(new ListaWiazana());

        }

        public ListaTab(ListaWiazana list){
            _list = list;
        }


        public void enqueue(Object value){
            _list.add(value);
        }
        
        public void wyswietl(){
            _list.wyswietl();
        }


        public Object dequeue() throws EmptyQueueException {
            
            if (isEmpty()) {

                throw new EmptyQueueException();

            }

            return _list.delete(0);

        }

        public void clear(){
            _list.clear();
        }


        public int size(){
            return _list.size();
        }


        public boolean isEmpty(){
            return _list.isEmpty();
        }


        public String toString(){
            return _list.toString();
        }

    }
