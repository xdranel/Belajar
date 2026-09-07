<?php

namespace App\Providers;

use App\Models\Person;
use App\Services\SayHello;
use Illuminate\Support\Facades\App;
use Illuminate\Support\Facades\Blade;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        App::singleton(SayHello::class, function () {
            return new SayHello();
        });
    }

    /**
     * Bootstrap any application services.
     *
     * @return void
     */
    public function boot()
    {
        Blade::directive('hello', function ($expression) {
/*            return "<?php echo (new App\Services\SayHello)->sayHello($expression); ?>";*/
            return "<?php echo 'Hello ' . $expression; ?>";
        });

        Blade::stringable(Person::class, function (Person $person) {
            return "{$person->name} : {$person->address}";
        });
    }
}
