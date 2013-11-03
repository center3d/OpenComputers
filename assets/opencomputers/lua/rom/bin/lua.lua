print("Lua 5.2.2 Copyright (C) 1994-2013 Lua.org, PUC-Rio")
local history = {}
local env = setmetatable({}, {__index=_ENV})
while term.isAvailable() do
  term.write("lua> ")
  local command = term.read(history)
  if command == nil then -- eof
    return
  end
  while #history > 10 do
    table.remove(history, 1)
  end
  local statement, result = load(command, "=stdin", "t", env)
  local expression = load("return " .. command, "=stdin", "t", env)
  local code = expression or statement
  if code then
    local result = table.pack(shell.execute(code))
    if not result[1] or result.n > 1 then
      print(table.unpack(result, 2, result.n))
    end
  else
    print(result)
  end
end