import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeTitleObserver implements Observer {

    // OBSERVER için zamanı izlemekten daha iyi bir fikir bulamadım mobil programlamada
    // liveData konseptinde kullanmıştım recyclerView favori silerken veri silindiğinde
    // recyclerView otomatik olarak notifyDatasetChange'i çağırıyordu
    private UpdateTitleCommand updateTitleCommand;
    private ScheduledExecutorService executor;

    public TimeTitleObserver(UpdateTitleCommand updateTitleCommand) {
        this.updateTitleCommand = updateTitleCommand;
        this.executor = Executors.newSingleThreadScheduledExecutor(); // main thread yanında yeni thread açılıyor
    }

    // Main thread swing tarafından kitli dolayısıyla diğer threadde time gözlenip saniye saniye basılacak
    // mobil programlamadaki liveData konsepti ile aynı
    public void update() {
        executor.scheduleAtFixedRate(updateTitleCommand, 0, 1, TimeUnit.SECONDS);
    }
}