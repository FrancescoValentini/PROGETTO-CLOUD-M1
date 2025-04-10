using backend_services.BackendServices;
using NoteLock_FrontEnd.Components;
using System.Net;
using NoteLock_FrontEnd.Utils;

var builder = WebApplication.CreateBuilder(args);

var allowedOrigins = "_customAllowedOrigins";

// Add services to the container.
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents()
    .AddInteractiveWebAssemblyComponents(); // <--- Aggiunto il supporto WASM

builder.Services.AddScoped<TokenService>();
builder.Services.AddHttpClient<AuthController>();
builder.Services.AddHttpClient<FolderController>();
builder.Services.AddHttpClient<NoteController>();
builder.Services.AddHttpClient<WhoamiController>();

builder.Services.AddCors(options => {
    options.AddPolicy(name: allowedOrigins,
                      policy => {
                          policy.WithOrigins("http://127.0.0.1");
                      });
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
}

app.UseStaticFiles();
app.UseAntiforgery();

app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode()
    .AddInteractiveWebAssemblyRenderMode(); // <--- Aggiunto WebAssembly Render Mode


app.Run();
