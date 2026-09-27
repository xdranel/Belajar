<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Listing extends Model
{
    /** @use HasFactory<\Database\Factories\ListingFactory> */
    use HasFactory;

    protected $fillable = [
        'title',
        'desc',
        'tags',
        'email',
        'link',
        'image',
        'approved',
    ];

    public function user(): BelongsTo
    {
        return $this->belongsTo(User::class);
    }

    // using "scope" laravel immediately apply the filter to the query
    // that way you can just call filter() and it will automatically apply the filter
    public function scopeFilter($query, array $filters)
    {
        // dd(request());
        if ($filters['search'] ?? false) {
            // dd($filters);
            $query->where(function ($query) {
                $query
                    ->where('title', 'like', '%' . request('search') . '%')
                    ->orWhere('desc', 'like', '%' . request('search') . '%');
            });
        }

        if ($filters['user_id'] ?? false) {
//             dd($filters);
            $query
                ->where('user_id', request('user_id'));
        }

        if ($filters['tag'] ?? false) {
            $query
                ->where('tags', 'like', '%' . request('tag') . '%');
        }

        if ($filters['disapproved'] ?? false) {
            $query->where('approved', false);
        }
    }
}
