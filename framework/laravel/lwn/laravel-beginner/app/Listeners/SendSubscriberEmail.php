<?php

namespace App\Listeners;

use App\Events\UserSubscribed;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Support\Facades\Mail;

class SendSubscriberEmail implements ShouldQueue
{
    /**
     * Create the event listener.
     */
    public function __construct()
    {
        //
    }

    /**
     * Handle the event.
     */
    public function handle(UserSubscribed $event): void
    {
        Mail::raw('Thank you for subscribing to our newsletter!',
            function ($message) use ($event) {
                $message->to($event->user->email);
                $message->subject('Thank you for subscribing!');
            });
    }
}
