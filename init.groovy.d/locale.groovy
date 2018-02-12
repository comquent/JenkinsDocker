
import org.jvnet.localizer.LocaleProvider;
import java.util.Locale;

LocaleProvider.setProvider(new LocaleProvider() {
    LocaleProvider original = LocaleProvider.getProvider();
    public Locale get() {
		     return new Locale("EN")
    }
});
