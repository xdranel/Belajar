<?php

namespace Tests\Feature;

use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Support\Facades\Log;
use Tests\TestCase;

class LoggingTest extends TestCase
{
    public function testLogging()
    {
        Log::info('This is a info log message.');
        Log::error('This is an error log message.');
        Log::warning('This is a warning log message.');
        Log::critical('This is a critical log message.');

        self::assertTrue(true);
    }

    public function testContext()
    {
        Log::info('This is a info log message.', ['context' => 'This is a context']);
        Log::info('This is a info log message.', ['context' => 'This is a context']);
        Log::info('This is a info log message.', ['context' => 'This is a context']);

        self::assertTrue(true);
    }

    public function testWithContext()
    {
//        Log::withContext(['user' => 'gendhi'])->info('This is a info log message.');
        Log::withContext(['user' => 'gendhi']);

        Log::info('This is a info log message.');


        self::assertTrue(true);
    }

    public function testChannel()
    {
        $slackChannel = Log::channel('slack');
        $slackChannel->error('Hello error slack');

        Log::info('Hello info');
        self::assertTrue(true);
    }

    public function testFileHandler()
    {
        $fileLogger = Log::channel('file');
        $fileLogger->info('Hello info file');
        $fileLogger->error('Hello error file');
        $fileLogger->warning('Hello warning file');
        $fileLogger->critical('Hello critical file');

        self::assertTrue(true);
    }


}
