export default function AuthLayout({ children }: { children: React.ReactNode }) {
  return (
    <div
      className="min-h-[calc(100vh-49px)] flex items-center justify-center px-4 sm:px-5 py-8 sm:py-12"
      style={{
        background:
          "radial-gradient(600px 340px at 15% 8%, #DCEEFA, transparent 60%), radial-gradient(500px 320px at 90% 92%, #F3F9FD, transparent 60%), #F5F9FC",
      }}
    >
      <div className="w-full max-w-[460px] bg-white border border-hairline rounded-[20px] px-5 sm:px-9 pt-8 sm:pt-10 pb-8 shadow-soft">
        {children}
      </div>
    </div>
  );
}
