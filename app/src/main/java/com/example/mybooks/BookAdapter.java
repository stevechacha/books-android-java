package com.example.mybooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public  class BookAdapter extends  RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    ArrayList<Book> books;

    public BookAdapter(ArrayList<Book>books){
        this.books=books  ;


    }

    @Override
    public BookViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context=parent.getContext ();
        View itemView= LayoutInflater.from ( context )
                .inflate ( R.layout.book_list_item,parent,false )  ;
        return new BookViewHolder ( itemView );
    }

    @Override
    public void onBindViewHolder( BookViewHolder holder, int position) {
        Book book=books.get ( position );
        holder.bind ( book );

    }

    @Override
    public int getItemCount() {
        return books.size ();
    }

    public class BookViewHolder extends  RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvAuthors;
        TextView tvDate;
        TextView tvPublisher;

        public BookViewHolder(View itemView) {
            super ( itemView );
            tvTitle=(TextView) itemView.findViewById ( R.id.tvTitle );
            tvAuthors=(TextView) itemView.findViewById ( R.id.tvAuthor ) ;
            tvDate=(TextView) itemView.findViewById ( R.id.tvPublishedDate );
            tvPublisher=(TextView) itemView.findViewById ( R.id.tvPublisher );
        }

        public void bind(Book book){
            tvTitle.setText ( book.title );
            String authors="";
            int i =0;
            for (String author:book.authors){
                authors+=author;
                i++;
                if (i<book.authors.length){
                    authors+= "," ;
                    
                }
            }

            tvAuthors.setText ( authors );
            tvDate.setText ( book.publishedDate );
            tvPublisher.setText ( book.publisher );
        }
    }
}
