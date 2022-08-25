package com.example.samplechatapp.Adapters;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.samplechatapp.Activities.MainActivity;
import com.example.samplechatapp.Activities.bookList;
//import com.example.samplechatapp.Activities.customList;
import com.example.samplechatapp.Activities.listview;
import com.example.samplechatapp.Activities.pdfview;
import com.example.samplechatapp.Models.request;
import com.example.samplechatapp.Models.user;
import com.example.samplechatapp.Models.utils;
import com.example.samplechatapp.R;
import com.example.samplechatapp.databinding.BookShortLayoutBinding;
import com.example.samplechatapp.Models.Book;
import com.example.samplechatapp.Models.alllists;
import com.example.samplechatapp.Models.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class BookRecycleViewAdapter extends RecyclerView.Adapter<BookRecycleViewAdapter.BookCategoryRecycleViewHolder> {

    BookShortLayoutBinding binding;

    private Context context;
    private ArrayList<Book> books;
    private Book book;
    private request rq, rq2;
    private user u;

//    private static String url;
//    private static Uri uri;
//    private static Intent intent3;

    Map newMap = new HashMap<String, ArrayList<Book>>();
    ArrayList<Book> list = new ArrayList<>();
    ArrayList<String[]> categorylist = new ArrayList<>();


    private int fragmentNo;

//    public BookRecycleViewAdapter(ArrayList<String[]> categorylist, Context context, int fragmentNo) {
//        this.context = context;
////        this.categorylist = new ArrayList<>();
//        this.categorylist = categorylist;
//        this.fragmentNo = fragmentNo;
////        System.out.println("BooksAdapter lists ArrayList -> " + this.lists.get(0));
//    }

    public BookRecycleViewAdapter(Context context, ArrayList<Book> books, int fragmentNo) {
        this.context = context;
        this.books = new ArrayList<>();
        this.books = books;
        this.fragmentNo = fragmentNo;
//        System.out.println("BooksAdapter lists ArrayList -> " + this.lists.get(0));
    }


    @NonNull
    @Override
    public BookCategoryRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.book_short_layout, parent, false);
        return new BookCategoryRecycleViewHolder(v);
    }

    /**
     * adding data to utils class
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull BookCategoryRecycleViewHolder holder, int position) {
        System.out.println("Starting to display books");
        book = books.get(position);
        int i = 1;
        for (Book b : books) {
            System.out.println((i++) + " => " + b);
        }


        holder.binding.bookName.setText(book.getName());
//        holder.binding.bookName.setText(utils.getName());

        Glide.with(context)
                .load(book.getImageUrl())
                .placeholder(R.drawable.ic_dummy_book)
                .into(holder.binding.bookimg);
//        FirebaseDatabase.getInstance().getReference().child("users").child(utils.getuID()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                System.out.println("snapshot.getValue() => "+snapshot.getValue());
//                u = snapshot.getValue(user.class);
//                String name = u.getName();
//                System.out.println("u.getName() => "+name);
////                                                        rq2.setSendername(u.getName());
//                rq.setSendername(name);
//                System.out.println("rq2.getSendername() => "+rq.getSendername());
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                rq.setSendername("failed to get user name");
//                System.out.println("unable to connect to user db => "+error);
//            }
//        });

        if (context.getClass().equals(MainActivity.class)) {

            holder.binding.bookimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    ImageView img = v.findViewById(R.id.bookimg);
//                    img.setOnClickListener(new View.OnClickListener() {
//                        @SuppressLint("WrongConstant")
//                        @Override
//                        public void onClick(View v) {
//                        Navigation.findNavController(v).navigate(R.id.);
                    System.out.println("book.getCurrentfragementid() -> " + v.getResources().getClass() + "\nR.layout.fragment_homefragment -> " + R.layout.fragment_homefragment);
                    Intent intent = new Intent(context, bookList.class);
                    intent.putExtra("category", books.get(position).getName());
//                    intent.putExtra("category", "Action & Adventure");
                    System.out.println("book.get(position).getName() => " + books.get(position).getName());
                    context.startActivity(intent);
//                        }
//                    });
                }
            });
        } else if (context.getClass().equals(bookList.class) || book.getFav()) {
            holder.binding.add.setVisibility(View.VISIBLE);

//            holder.binding.add.setVisibility(View.VISIBLE);
            holder.binding.bookinfo.setVisibility(View.VISIBLE);
            holder.binding.bookauthor.setVisibility(View.VISIBLE);

//            holder.binding.bookimg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    ImageView img = v.findViewById(R.id.bookimg);
//
////                    ================== download ============================
//                    img.setOnClickListener(new View.OnClickListener() {
//                        @SuppressLint("WrongConstant")
//                        @Override
//                        public void onClick(View v) {
//                            System.out.println("Clicked on image => "+book.getName());
////                            context.startActivity(new Intent(context, book.class));
//                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                            builder.setMessage("Do you want to download ?");
//                            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
////                                    TODO: place downloading image url
////                                    download the book
//                                    FirebaseDatabase.getInstance().getReference().child("requestlist").child(utils.getuID()).addValueEventListener(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                            if (snapshot.exists()) {
//
//                                                request rq = snapshot.getValue(request.class);
//                                                if (rq.isRequestgranted()) {
////                                                    Uri uri2 = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()
////                                                            + File.separator + "new folder"+ "/index.html"));
//                                                    Uri uri2 = Uri.parse(book.getDownloadUrl());
//                                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri2);
//                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                                    intent.setClassName("com.android.chrome", "com.google.android.apps.chrome.Main");
////                                                    context.startActivity(intent);
////                                                    try {
//
////                                                        context.startActivity(intent);
////                                                    } catch (ActivityNotFoundException ex) {
////                                                        try {
////                                                            intent.setPackage(null);
////                                                            context.startActivity(intent);
////                                                        } catch (Exception e) {
////                                                        }
////                                                    }
//                                                } else {
//                                                    new AlertDialog.Builder(context)
//                                                            .setTitle("Request")
//                                                            .setMessage("Your request is currently in reviewing state")
//                                                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                                                                @Override
//                                                                public void onClick(DialogInterface dialog, int which) {
////                                                                            exit
//                                                                }
//                                                            })
//                                                            .create()
//                                                            .show();
//                                                }
//                                            } else {
//                                                rq = new request();
//                                                rq.setBookname(book.getName());
//                                                rq.setSenderid(utils.getuID());
//                                                rq.setBookurl(book.getDownloadUrl());
////                                                rq.setSendername(u.getName());
////                                                rq.setSendername(rq2.getSendername());
//                                                FirebaseDatabase.getInstance().getReference().child("requestlist")
//                                                        .child(rq.getSenderid()).setValue(rq).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<Void> task) {
//                                                        if (task.isSuccessful()) {
//                                                            new AlertDialog.Builder(context)
//                                                                    .setTitle("Request")
//                                                                    .setMessage("Your request has submitted")
//                                                                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                                                                        @Override
//                                                                        public void onClick(DialogInterface dialog, int which) {
////                                                                            exit
//                                                                        }
//                                                                    })
//                                                                    .create()
//                                                                    .show();
//                                                        } else {
//                                                            new AlertDialog.Builder(context)
//                                                                    .setTitle("Request")
//                                                                    .setMessage("Failed to submit request")
//                                                                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                                                                        @Override
//                                                                        public void onClick(DialogInterface dialog, int which) {
////                                                                            exit
//                                                                        }
//                                                                    })
//                                                                    .create()
//                                                                    .show();
//                                                        }
//                                                    }
//                                                });
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError error) {
//
//                                        }
//                                    });
//                                }
//                            });
//                            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
////                        do nothing
//                                    dialog.dismiss();
//                                }
//                            });
//                            AlertDialog dialog = builder.create();
//                            dialog.show();
//                        }
//                    }
//                    );
//                }
//            });
            holder.binding.bookimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("context.getClass() => " + context.getClass());
//                    AlertDialog dialog = new AlertDialog.Builder(context)
//                            .setMessage("Submit request")
//                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {

//                                }
//                            })
//                    .create();
//                    dialog.show();
                    FirebaseDatabase.getInstance().getReference().child("requestlist").addValueEventListener(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean found = true;
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                request rq = snapshot1.getValue(request.class);
                                if (rq.getSenderid().equals(utils.getInstance().getuID())) {

                                    System.out.println("rq.isRequestgranted() => " + rq.isRequestgranted());
                                    System.out.println("snapshot1.child(\"requestgranted\").getValue(boolean.class) => " + snapshot1.child("requestgranted").getValue(boolean.class));

                                    if (snapshot1.child("requestgranted").getValue(boolean.class)) {
                                        if (snapshot1.child("bookname").getValue(String.class).equals(books.get(position).getName())) {
                                            holder.binding.add.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_cross_circle_24));
                                            holder.binding.remove.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    FirebaseDatabase.getInstance().getReference().child("requestlist").child(utils.getuID()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){
                                                                Toast.makeText(context,"sucessfully returned",Toast.LENGTH_LONG).show();
                                                            }
                                                            else if(!task.isSuccessful()){
                                                                Toast.makeText(context,"Return failed",Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                            Toast.makeText(context, "Opening book...", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(context, pdfview.class);
                                            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                            intent.putExtra("url", books.get(position).getDownloadUrl());
                                            context.startActivity(intent);
                                        } else {
                                            holder.binding.remove.setVisibility(View.INVISIBLE);
                                            Toast.makeText(context, "You had already placed request for " + rq.getBookname(), Toast.LENGTH_SHORT).show();
                                            Toast.makeText(context, "One book at a time or return it for new request", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(context, "You had already placed request for " + rq.getBookname(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(context, "One book at a time or return it for new request", Toast.LENGTH_LONG).show();
                                    }
                                    found = true;
                                } else {
                                    found = false;
                                }
                            }
                            if (!found) {
                                request rq = new request();
                                rq.setBookname(book.getName());
                                rq.setSenderid(utils.getuID());
                                rq.setBookurl(book.getDownloadUrl());
                                rq.setSendername(u.getName());
                                rq.setSendername(rq2.getSendername());
                                FirebaseDatabase.getInstance().getReference().child("requestlist").child(utils.getuID()).setValue(rq).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(context, "Your request for " + holder.binding.bookName.getText() + " has submitted", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context, "Failed to submit request", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(context, "Not able to connect to database", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            System.out.println("context => " + context + "\nfragmentNo => " + fragmentNo);
            System.out.println("holder.binding.star.setVisibility \n book.id => " + book.id);

        }

        holder.binding.bookauthor.setText("Author : " + book.getAuthor());

//================================================================================================
//        2 is randomly chosen, can be given other number

////            =========================== fav remove button  ========================================

        if (fragmentNo == 2 || context.getClass().equals(listview.class)) {

            holder.binding.add.setVisibility(View.GONE);
            holder.binding.bookinfo.setVisibility(View.VISIBLE);
            holder.binding.bookauthor.setVisibility(View.VISIBLE);
            holder.binding.remove.setVisibility(View.VISIBLE);
        }

        holder.binding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).setMessage("Remove from favourites ?")
                        .setNegativeButton("Yes ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                alllists.getInstance().removefromFav(books.get(position));
                                alllists.getInstance().addBooktoAllBook(book);
                                book.setFav(false);
                                Toast.makeText(context, book.getName() + " removed sucessfully", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                        })
                        .setPositiveButton("no ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                           do nothing
                            }
                        })
                        .create()
                        .show();
            }
        });


////            =========================== booklist plus button ========================================

        holder.binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("star btn clicked");
                System.out.println("context.getClass() => " + context.getClass());
//                if (context.getClass().equals(bookList.class)) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context)
                        .setMessage("ADD TO : ")
                        .setNegativeButton("Wishlist", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alllists.getInstance().addtoFav(book);
                                book.setFav(true);
                                Toast.makeText(context, "book added", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("New/ExistingList", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//==================================================================================
//                                    customized prompt input
                                // get prompts.xml view
                                LayoutInflater li = LayoutInflater.from(context);
                                View promptsView = li.inflate(R.layout.userprompt, null);

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        context);

                                // set prompts.xml to alertdialog builder
                                alertDialogBuilder.setView(promptsView);

                                final EditText userInput = (EditText) promptsView
                                        .findViewById(R.id.editTextDialogUserInput);

                                // set dialog message
                                alertDialogBuilder
                                        .setCancelable(false)
                                        .setPositiveButton("ADD",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        // get utils input ,create list and add to userbooklist
                                                        newMap = alllists.getInstance().getuserbooklist();
                                                        String input = userInput.getText().toString();
                                                        System.out.println("newMap.size() => " + newMap.size());
                                                        list.clear();
                                                        System.out.println("userInput.getText().toString() => " + userInput.getText().toString());
                                                        if (newMap.size() != 0) {
                                                            Set s = alllists.getInstance().getuserbooklistKeys();
                                                            System.out.println("s =>" + s);
                                                            for (Object b : s) {
                                                                System.out.println("b.toString() =>" + b.toString());
                                                                if (userInput.getText().toString() == b.toString()) {
                                                                    list = (ArrayList<Book>) newMap.get(input);

                                                                    System.out.println("list => " + list);

                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        list.add(book);
                                                        System.out.println("list => " + list);
                                                        newMap.put(userInput.getText().toString(), list);
//                                                            if(newMap.size()==0){
//                                                                System.out.println("*************creating new map ****************8");
//                                                                list.add(book);
//                                                                newMap.put(userInput.getText().toString(), list);
//                                                            }


                                                        try {

                                                            alllists.getInstance().setuserbooklist(newMap);

                                                        } catch (Exception e) {
                                                            System.out.println("Exception : " + e);
                                                            Toast.makeText(context, "list creation failed", Toast.LENGTH_SHORT)
                                                                    .show();
                                                        }
                                                        Toast.makeText(context, "Book added", Toast.LENGTH_SHORT)
                                                                .show();
                                                        System.out.println(alllists.getInstance().getuserbooklist());
                                                    }
                                                })
                                        .setNegativeButton("Cancel",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });

//                                  //   create alert dialog
                                AlertDialog alertDialog = alertDialogBuilder.create();
//
//                                    // show it
                                alertDialog.show();
                            }
                        });
//                            .setNeutralButton("Existing",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which){
//                                    alllists.getInstance().setTemp(null);
//                                    alllists.getInstance().setTemp(book);
//                                    Intent intent = new Intent(context,listview.class);
//                                    intent.putExtra("add","");
//                                    context.startActivity(intent);
//                                }
//                            });
//                            .create();
                AlertDialog dialog = builder1.create();
//                dialog.show();
////===================================================================================
            }
//            }
        });
//        holder.binding.add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context)
//                        .setMessage("Add Btn")
//                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });
        holder.binding.bookinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context).setMessage(book.getName() + "\n\n" + book.getShortDesc() + "\n\n" + book.getDesc())
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                        do nothing
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

//    private static void checkFileTypeByUrl() {
//
//        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
//        intent3.setDataAndType(uri, mimeType);
//    }
//
//    private static void setupBase(File fileDTO) {
//        url = fileDTO.getUrl();
//        uri = Uri.parse(url);
//        intent3 = new Intent(Intent.ACTION_VIEW, uri);
//    }


    public class BookCategoryRecycleViewHolder extends RecyclerView.ViewHolder {

        public BookShortLayoutBinding binding;

        //        BookShortLayoutBinding binding;//this is alternative for findViewById()
        public BookCategoryRecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = BookShortLayoutBinding.bind(itemView);
        }
    }
}
